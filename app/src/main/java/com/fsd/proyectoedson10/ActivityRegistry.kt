package com.fsd.proyectoedson10

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ActivityRegistry : AppCompatActivity() {

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
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }


}