package com.fsd.proyectoedson10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho
import com.google.firebase.database.*


data class User(
    var name: String = "", var lastName: String = "", var password : String = "", var avatar : String= "", var status : Int = 0
){
    var id : String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}

class LoginActivity : AppCompatActivity() {
    private lateinit var textRegistry: TextView
    private lateinit var btnLogin: Button
    private lateinit var etUser: EditText
    private lateinit var etPass: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Stetho.initializeWithDefaults(this)

        textRegistry = findViewById(R.id.editText_registry)
        btnLogin = findViewById(R.id.btn_login)
        etUser = findViewById(R.id.editText_user)
        etPass = findViewById(R.id.editText_pass)


        textRegistry.setOnClickListener{
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val usersRef =  database.getReference("user").child("kike@hotmail,com")
            usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }
                override fun onDataChange(p0: DataSnapshot) {
                    var user : User? = p0.getValue(User::class.java)
                    user?.id = p0.key

                    Log.d("hola", user!!.password.toString())
                }
            })
        }




            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            val user = etUser.text.toString()
        }


}



