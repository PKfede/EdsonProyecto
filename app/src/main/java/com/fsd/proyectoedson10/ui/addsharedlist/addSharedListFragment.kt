package com.fsd.proyectoedson10.ui.addsharedlist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.MainActivity

import com.fsd.proyectoedson10.R
import com.fsd.proyectoedson10.toDoList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import yuku.ambilwarna.AmbilWarnaDialog
import java.util.*


data class SharedToDoList(
    var listId :String = "",var userId: String = ""
){
    var id : String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SharedToDoList

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}

data class SharedListNotification(
    var userId: String = "", var date : String, var listId: String, var sender : String, var listName : String
){
    var id : String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SharedToDoList

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}

class addSharedListFragment : Fragment() {



    companion object {
        fun newInstance() = addSharedListFragment()
    }

    private var listOfUsers : MutableList<String> = mutableListOf()

    private lateinit var viewModel: addSharedListViewModel
    private lateinit var colorButton : Button
    private var defaultColor  : Int = 0
    private lateinit var btnSave : Button
    private lateinit var edName : EditText
    private lateinit var rbCoche : RadioButton
    private lateinit var rbPintura : RadioButton
    private lateinit var rbAvion : RadioButton
    private lateinit var rbCarreola : RadioButton
    private lateinit var rbTrabajo : RadioButton
    private lateinit var rbBici : RadioButton
    private lateinit var rbSuper : RadioButton
    private lateinit var rbMusica : RadioButton
    private lateinit var rbMartillo : RadioButton
    private lateinit var rbImagen : RadioButton
    private lateinit var btnAddParticipants: Button
    private lateinit var edAddParticipants: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add_shared_list, container, false)

        edAddParticipants = view.findViewById(R.id.adParticipants)
        btnAddParticipants = view.findViewById(R.id.buttonAddParticipant)
        btnSave = view.findViewById(R.id.buttonSave)
        colorButton = view.findViewById(R.id.buttonColor)
        defaultColor = ContextCompat.getColor(view.context,R.color.colorPrimary)
        edName = view.findViewById(R.id.editTextNameList)
        rbCoche = view.findViewById(R.id.radioCar)
        rbPintura = view.findViewById(R.id.radioPainting)
        rbAvion = view.findViewById(R.id.radioPlane)
        rbCarreola = view.findViewById(R.id.radioTrolley)
        rbTrabajo = view.findViewById(R.id.radioWork)
        rbBici = view.findViewById(R.id.radioBike)
        rbSuper = view.findViewById(R.id.radiMarketPlace)
        rbMusica = view.findViewById(R.id.radioMusic)
        rbMartillo = view.findViewById(R.id.radioHammer)
        rbImagen = view.findViewById(R.id.radioPicture)

        //val nameList = AppDatabase.getList()

        val db = AppDatabase.getAppDatabase(view.context)
        Stetho.initializeWithDefaults(view.context)

        val listas = db.ListDAO().getAll()


        colorButton.setOnClickListener( object: View.OnClickListener{

            override fun onClick(v : View) {
                openColorPicker(view.context)
            }
        })

        val menu = AppDatabase.getNav().menu
        //val background = AppDatabase.getBackground()

        btnAddParticipants.setOnClickListener{
            var modifiedEmail = edAddParticipants.text.toString().replace("""[.]""".toRegex(), ",")
            listOfUsers.add(modifiedEmail)
            edAddParticipants.text.clear()
            Log.d("Hol2a", listOfUsers.size.toString())
        }
        btnSave.setOnClickListener{


            val nameString : Int = when {
                rbCoche.isChecked -> R.drawable.auto
                rbPintura.isChecked -> R.drawable.pintura
                rbAvion.isChecked -> R.drawable.avion
                rbCarreola.isChecked -> R.drawable.carreola
                rbTrabajo.isChecked -> R.drawable.trabajo
                rbBici.isChecked -> R.drawable.bici
                rbSuper.isChecked -> R.drawable.supermercado
                rbMusica.isChecked -> R.drawable.musica
                rbMartillo.isChecked -> R.drawable.martillo
                else -> R.drawable.foto
            }
//
            var rnds = (0..1000000).random()
            var list = ListETY(db.UserDAO().getUser().id)
            list.idList = rnds.toString()
            list.listColor = defaultColor.toString()
            list.listIcon = nameString.toString()
            list.listName = edName.text.toString()
            db.ListDAO().insertList(list)

            val dbFirebase = FirebaseDatabase.getInstance()
            val dbRef = dbFirebase.getReference("list")

            var listToFirebase = toDoList(list.listName,db.UserDAO().getUser().id,list.listIcon,list.listColor,1,"0")
            listToFirebase.id = rnds.toString()
            dbRef.child(rnds.toString()).setValue(listToFirebase)

//            agrega a los invitados
            addAllUsers(list, view.context)

            (activity as MainActivity).fillNavigationSharedList()
            edName.text.clear()
            Toast.makeText(view.context, "Lista agregada", Toast.LENGTH_SHORT).show()


        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(addSharedListViewModel::class.java)

    }

    fun newDateFormat(date : Date) : String
    {
        var newDate : String 
        var dateString : String = date.toString()

        newDate = "${dateString[8]}${dateString[9]}-${dateString[4]}${dateString[5]}${dateString[6]}-${dateString[24]}${dateString[25]}${dateString[26]}${dateString[27]}"

        return newDate

    }

    fun openColorPicker(context: Context){
        var colorPicker = AmbilWarnaDialog(context, defaultColor, object: AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {
            }
            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {

                defaultColor = color
                colorButton.setBackgroundColor(defaultColor)
            }
        })
        colorPicker.show()
    }
    private fun addAllUsers(list:ListETY, context: Context){

        Log.d("Hol2a", listOfUsers.size.toString())
        //verifica que la lista de usuarios agregados nio este vacia y luego agrega en sharedlist un record por cada usuario en la lista
        if(listOfUsers.isNotEmpty()){
            for(x in listOfUsers){
                val dbFirebase = FirebaseDatabase.getInstance()

                var rnds = (0..1000000).random()
                val listRef = dbFirebase.getReference("list")

                var listToFirebase = toDoList(list.listName,x,list.listIcon,list.listColor,1,"0")
                listToFirebase.id = rnds.toString()
                listRef.child(rnds.toString()).setValue(listToFirebase)

                //Notificaciones
                val notRef = dbFirebase.getReference("notification")
                var rnds3 = (0..1000000).random()
                var currentTime : Date = Calendar.getInstance().time
                var currentDate = newDateFormat(currentTime)

                var notificationToFirebase = SharedListNotification(x,currentDate.toString(),list.idList,list.userId,list.listName)

                notRef.child(rnds3.toString()).setValue(notificationToFirebase)
            }
        }
    }
}
