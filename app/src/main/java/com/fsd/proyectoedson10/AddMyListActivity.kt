package com.fsd.proyectoedson10

import android.app.usage.UsageEvents.Event.NONE
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.DAO.ListDAO
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.UserETY
import com.fsd.proyectoedson10.R
import com.google.android.material.navigation.NavigationView
import yuku.ambilwarna.AmbilWarnaDialog
import java.util.*

class AddMyListActivity : AppCompatActivity() {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_my_list)




        btnSave = findViewById(R.id.buttonSave)
        colorButton = findViewById(R.id.buttonColor)
        defaultColor = ContextCompat.getColor(this,R.color.colorPrimary)
        edName = findViewById(R.id.editTextNameList)
        rbCoche = findViewById(R.id.radioCar)
        rbPintura = findViewById(R.id.radioPainting)
        rbAvion = findViewById(R.id.radioPlane)
        rbCarreola = findViewById(R.id.radioTrolley)
        rbTrabajo = findViewById(R.id.radioWork)
        rbBici = findViewById(R.id.radioBike)
        rbSuper = findViewById(R.id.radiMarketPlace)
        rbMusica = findViewById(R.id.radioMusic)
        rbMartillo = findViewById(R.id.radioHammer)
        rbImagen = findViewById(R.id.radioPicture)

        val nameList = AppDatabase.getList()

        val db = AppDatabase.getAppDatabase(this)
        Stetho.initializeWithDefaults(this)

        val listas = db.ListDAO().getAll()

        colorButton.setOnClickListener( object: View.OnClickListener{

          override fun onClick(v : View) {
              openColorPicker()
          }
            })




         val menu = AppDatabase.getNav().menu
         val background = AppDatabase.getBackground()

        btnSave.setOnClickListener{

            val nameString : Int

            if(rbCoche.isChecked)
            {
                nameString = R.drawable.auto
            }
            else if(rbPintura.isChecked)
            {
                nameString = R.drawable.pintura
            }
            else if(rbAvion.isChecked)
            {
                nameString = R.drawable.avion
            }
            else if(rbCarreola.isChecked)
            {
                nameString = R.drawable.carreola
            }
            else if(rbTrabajo.isChecked)
            {
                nameString = R.drawable.trabajo
            }
            else if(rbBici.isChecked)
            {
                nameString = R.drawable.bici
            }
            else if(rbSuper.isChecked)
            {
                nameString = R.drawable.supermercado
            }
            else if(rbMusica.isChecked)
            {
                nameString = R.drawable.musica
            }
            else if(rbMartillo.isChecked)
            {
                nameString = R.drawable.martillo
            }
            else
            {
                nameString = R.drawable.foto
            }
            menu.add(R.id.group2,Menu.NONE,1,edName.text.toString()).setIcon(nameString).setOnMenuItemClickListener {
                nameList.setText(edName.text.toString())
                val drawerLayout = AppDatabase.getDrawer()
                drawerLayout.closeDrawers()
                background.setBackgroundColor(db.ListDAO().selectList(db.ListDAO().selectByName(edName.text.toString()).idList).listColor.toInt())
                true
            }

            var list = ListETY("AAA")
            list.listColor = defaultColor.toString()
            list.listIcon = nameString.toString()
            list.listName = edName.text.toString()
            db.ListDAO().insertList(list)
            finish()
        }

    }

    fun openColorPicker(){
        var colorPicker = AmbilWarnaDialog(this, defaultColor, object:AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {
            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {

                defaultColor = color
                colorButton.setBackgroundColor(defaultColor)
            }
        })

        colorPicker.show()

    }
}