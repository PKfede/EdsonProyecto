package com.fsd.proyectoedson10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fsd.proyectoedson10.DB.Entities.UserETY
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class User(var name: String = "", var lastName: String = "", var password : String = "", var avatar : String= "", var status : Int = 0)

class RegistryActivity : AppCompatActivity() {

    private lateinit var btnRegistry : Button
    private lateinit var avatar : EditText
    private lateinit var etUserName : EditText
    private lateinit var etUserLastName : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPass : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)



        btnRegistry = findViewById(R.id.btn_registry)
        avatar = findViewById(R.id.editText_nickName)
        etUserName= findViewById(R.id.editText_userName)
        etUserLastName= findViewById(R.id.editText_userLastName)
        etEmail= findViewById(R.id.editText_email)
        etPass = findViewById(R.id.editText_userPass)


        btnRegistry.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val dbRef = database.getReference("user")

            val user = User(etUserName.text.toString(),etUserLastName.text.toString(),etPass.text.toString(),avatar.text.toString(),0)

            var modifiedEmail = etEmail.text.toString().replace("""[.]""".toRegex(),",")

            val userRef = database.getReference("user").child(modifiedEmail)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.value == null)
                    {
                        dbRef.child(modifiedEmail).setValue(user)
                        val intent = Intent(this@RegistryActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this@RegistryActivity, "Ya existe el usuario", Toast.LENGTH_LONG).show()
                    }
                }
            })





        }

    }


}