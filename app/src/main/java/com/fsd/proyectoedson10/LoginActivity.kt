package com.fsd.proyectoedson10

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.DAO.ListDAO
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.UserETY
import com.fsd.proyectoedson10.DB.Network
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
    lateinit var emailPattern: String



    // var currentUser = AppDatabase.getAppDatabase(this).UserDAO().getUser().name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = AppDatabase.getAppDatabase(this)
        Stetho.initializeWithDefaults(this)
        val x = db.UserDAO().getAll()

        textRegistry = findViewById(R.id.editText_registry)
        btnLogin = findViewById(R.id.btn_login)
        etUser = findViewById(R.id.editText_user)
        etPass = findViewById(R.id.editText_pass)

        emailPattern = "[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.+[a-z]+"

//        var lastUser = AppDatabase.getAppDatabase(this).UserDAO().getUser().id
//       var lastPass = AppDatabase.getAppDatabase(this).UserDAO().getUser().password


        // db.UserDAO().getUser().isLogged


        textRegistry.setOnClickListener {
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        }

        // etUser.setText(lastUser)


        if (db.UserDAO().getUser() != null) {
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

            if (!Network.isConnected(this@LoginActivity)) {

                val db = AppDatabase.getAppDatabase(this@LoginActivity)

                var auxUser = db.UserDAO().getUser().id
                var auxLogin = db.UserDAO().getUser().password

                if (etUser.text.toString().trim().length > 0) {
                    if (etPass.text.toString().trim().length > 0) {
                        if (etUser.text.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                            if (auxUser == modifiedEmail && auxLogin == etPass.text.toString()) {

                                if (db.UserDAO().getUser().isLogged == 0) {
                                    db.UserDAO().updateByNameTo1(db.UserDAO().getUser().name)
                                }
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Datos incorrectos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Ingresar un e-mail valido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "No lleno el user",
                            Toast.LENGTH_SHORT
                        ).show()
                        etPass.setHintTextColor(Color.RED)
                    }
                }else{
                    Toast.makeText(
                        this@LoginActivity,
                        "No lleno el user",
                        Toast.LENGTH_SHORT
                    ).show()
                    etUser.setHintTextColor(Color.RED)
                }

            } else {
                usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (etUser.text.toString().trim().length > 0) {
                            if (etPass.text.toString().trim().length > 0) {
                                if (etUser.text.toString().trim { it <= ' ' }.matches(
                                        emailPattern.toRegex())) {
                                    if (p0.exists()) {

                                        //A PARTIR DE AQUÍ SE RECUPERA LA INFORMACIÓN DE LAS LISTAS DEL USUARIO QUE SE ESTÁ LOGEANDO
                                        // val listRef = database.getReference("listasproyecto").child("list").orderByChild("idUser").equalTo(etUser.text.toString())

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

                                                val db =
                                                    AppDatabase.getAppDatabase(this@LoginActivity)
                                                db.UserDAO().deleteAll()
                                                db.UserDAO().insertUser(usuarioRoom)

                                                if (db.UserDAO().getUser().isLogged == 0) {
                                                    db.UserDAO().updateByNameTo1(usuarioRoom.name)
                                                }

                                                val intent =
                                                    Intent(
                                                        this@LoginActivity,
                                                        MainActivity::class.java
                                                    )

                                                val listRef = database.getReference("list").orderByChild("idUser").equalTo(modifiedEmail)


                                                //referencia = database.getReference("app").child("list").orderByChild("idUser").equalTo(auxUser)

                                                listRef.addListenerForSingleValueEvent(object : ValueEventListener{
                                                    override fun onCancelled(p0: DatabaseError) {
                                                    }

                                                    override fun onDataChange(p0: DataSnapshot) {
                                                        if(p0.exists())
                                                        {

                                                            val children = p0!!.children
                                                            children.forEach{
                                                                var listColor = it.child("color").value
                                                                var listIcon = it.child("icon").value
                                                                var listName = it.child("name").value
                                                                var listIdUser = it.child("idUser").value
                                                                var listIdList = it.child("idList").value

                                                                var listToDatabase = ListETY(listIdUser.toString())
                                                                listToDatabase.listColor = listColor.toString()
                                                                listToDatabase.listIcon = listIcon.toString()
                                                                listToDatabase.listName = listName.toString()
                                                                listToDatabase.idList = listIdList.toString()

                                                                db.ListDAO().insertList(listToDatabase)
                                                            }
                                                            Log.d("p0",p0.toString())
                                                        }
                                                    }
                                                })
                                                startActivity(intent)
                                                finish()
                                            } else {
                                                Toast.makeText(
                                                    this@LoginActivity,
                                                    "Correo no validado",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                        } else {
                                            Toast.makeText(
                                                this@LoginActivity,
                                                "Datos incorrectos",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "No existe el user",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        etUser.setHintTextColor(Color.RED)
                                    }
                                } else {

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Please enter valid email",
                                        Toast.LENGTH_SHORT
                                    ).show()



                                }
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "No lleno la contraseña",
                                    Toast.LENGTH_SHORT
                                ).show()
                                etPass.setHintTextColor(Color.RED)
                            }
                        }
                        else{
                            Toast.makeText(
                                this@LoginActivity,
                                "No lleno el user",
                                Toast.LENGTH_SHORT
                            ).show()
                            etUser.setHintTextColor(Color.RED)
                        }
                    }
                })
            }

        }

    }
}
