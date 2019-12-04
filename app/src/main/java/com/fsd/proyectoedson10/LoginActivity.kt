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
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.UserETY
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


   // var currentUser = AppDatabase.getAppDatabase(this).UserDAO().getUser().name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val db = AppDatabase.getAppDatabase(this)
        Stetho.initializeWithDefaults(this)

        textRegistry = findViewById(R.id.editText_registry)
        btnLogin = findViewById(R.id.btn_login)
        etUser = findViewById(R.id.editText_user)
        etPass = findViewById(R.id.editText_pass)

//        var lastUser = AppDatabase.getAppDatabase(this).UserDAO().getUser().id
//       var lastPass = AppDatabase.getAppDatabase(this).UserDAO().getUser().password


      // db.UserDAO().getUser().isLogged



        textRegistry.setOnClickListener{
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        }

       // etUser.setText(lastUser)


       if(db.UserDAO().getUser() != null) {
           if (db.UserDAO().getUser().isLogged == 1) {
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
               finish()
           }
       }


           btnLogin.setOnClickListener {

               var modifiedEmail = etUser.text.toString().replace("""[.]""".toRegex(), ",")
               val database = FirebaseDatabase.getInstance()
               val usersRef = database.getReference("user").child(modifiedEmail)
               usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                   override fun onCancelled(p0: DatabaseError) {

                   }

                   override fun onDataChange(p0: DataSnapshot) {
                       if (p0.exists()) {
                           var user: User? = p0.getValue(User::class.java)
                           user?.id = p0.key

                           // var auxUser =  Log.d("email", user!!.id.toString()).toString()
                           // var auxLogin =  Log.d("pass", user!!.password.toString()).toString()
                           var auxUser = user!!.id.toString()
                           var auxLogin = user.password

                           if (auxUser == modifiedEmail && auxLogin == etPass.text.toString()) {
                               if (user.status == 1) {
                                   val usuarioRoom = UserETY(
                                       user.id.toString(),
                                       user.name,
                                       user.lastName,
                                       user.password,
                                       user.avatar,
                                       1,
                                       0,
                                       0
                                   )

                                   val db = AppDatabase.getAppDatabase(this@LoginActivity)
                                   db.UserDAO().deleteAll()
                                   db.UserDAO().insertUser(usuarioRoom)

                                   if (db.UserDAO().getUser().isLogged == 0) {
                                       db.UserDAO().updateByNameTo1(usuarioRoom.name)
                                   }

                                   val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                   startActivity(intent)
                                   finish()
                               } else {
                                   Toast.makeText(
                                       this@LoginActivity,
                                       "Correo no validado",
                                       Toast.LENGTH_LONG
                                   ).show()
                               }

                           } else {
                               Toast.makeText(
                                   this@LoginActivity,
                                   "Datos incorrectos",
                                   Toast.LENGTH_LONG
                               ).show()
                           }
                       } else {
                           Toast.makeText(
                               this@LoginActivity,
                               "Usuario no existe",
                               Toast.LENGTH_LONG
                           ).show()
                       }

                   }
               })
           }

        }
    private fun onDeleteUser(){
       // AppDatabase.getAppDatabase(this).UserDAO().deleteUserById(currentUser!!.)

    }


}



