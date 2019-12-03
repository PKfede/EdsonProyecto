package com.fsd.proyectoedson10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.fsd.proyectoedson10.DB.Entities.UserETY
import com.google.firebase.database.FirebaseDatabase

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

            dbRef.child(etEmail.text.toString()).setValue(user)


            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


}