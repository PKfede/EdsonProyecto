package com.fsd.proyectoedson10

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class RegistryActivity : AppCompatActivity() {

    private lateinit var btnRegistry: Button
    private lateinit var etUserName: EditText
    private lateinit var etUserLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText

    private lateinit var imageBlackWoman : ImageView
    private lateinit var imageGrayWoman : ImageView
    private lateinit var imageGreenWoman : ImageView
    private lateinit var imageBlackMan : ImageView
    private lateinit var imageGrayMan : ImageView
    private lateinit var imageWeroMan: ImageView

    private lateinit var currentImageView: ImageView
    var selectedIcon = 0
    var image = 1;



    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)



        btnRegistry = findViewById(R.id.btn_registry)
        etUserName = findViewById(R.id.editText_userName)
        etUserLastName = findViewById(R.id.editText_userLastName)
        etEmail = findViewById(R.id.editText_email)
        etPass = findViewById(R.id.editText_userPass)


        imageBlackWoman = findViewById(R.id.ImageBlackWoman)
        imageGrayWoman  = findViewById(R.id.GrayWoman)
        imageGreenWoman = findViewById(R.id.ImageGreenWoman)
        imageBlackMan  = findViewById(R.id.ImageBlackMan)
        imageGrayMan  = findViewById(R.id.ManGray)
        imageWeroMan = findViewById(R.id.ImageWeroMan)


        currentImageView = imageBlackWoman
        currentImageView.foreground.alpha = 0
        selectedIcon = R.drawable.mujer_negra_rara

        imageBlackWoman.setOnClickListener {
            image = 1
            changeSelectedIcon(
                imageBlackMan,
                R.drawable.mujer_negra_rara
            )
        }
        imageGrayWoman.setOnClickListener {
            image = 2
            changeSelectedIcon(
                imageGrayWoman,
                R.drawable.mujer_pelo_gris
            )
        }
        imageGreenWoman.setOnClickListener {
            image = 3
            changeSelectedIcon(
                imageGreenWoman,
                R.drawable.mujer_verde
            )
        }
        imageBlackMan.setOnClickListener {
            image = 4
            changeSelectedIcon(
                imageBlackMan,
                R.drawable.hombre_negro_calvo
            )
        }
        imageGrayMan.setOnClickListener {
            image = 5
            changeSelectedIcon(
                imageGrayMan,
                R.drawable.hombre_pelo_gris
            )
        }
        imageWeroMan.setOnClickListener {
            image = 6
            changeSelectedIcon(
                imageWeroMan,
                R.drawable.hombre_wero
            )
        }

        btnRegistry.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val dbRef = database.getReference("user")

            val user = User(
                etUserName.text.toString(),
                etUserLastName.text.toString(),
                etPass.text.toString(),
                image.toString()
            )

            var modifiedEmail = etEmail.text.toString().replace("""[.]""".toRegex(), ",")

            val userRef = database.getReference("user").child(modifiedEmail)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.value == null) {
                        dbRef.child(modifiedEmail).setValue(user)
                        val intent = Intent(this@RegistryActivity, LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            this@RegistryActivity,
                            "Usuario creadoo",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@RegistryActivity,
                            "Ya existe el usuario",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })

        }




    }

    @SuppressLint("NewApi")
    private fun changeSelectedIcon(selectedImageView: ImageView, idResource: Int) {
        if (currentImageView == selectedImageView && selectedIcon == idResource) return

        //IMAGEVIEW PREVIO-SELECCIONADO CON OSCURIDAD (OPACACIDAD :)
        currentImageView.foreground.alpha = 255

        //IMAGEVIEW SET NUEVO ICONO

        currentImageView = selectedImageView
        //IMAGEVIEW NUEVO ICONO (IMAGEVIEW) SIN OSCURIDAD
        currentImageView.foreground.alpha = 0
        selectedIcon = idResource

    }


}


