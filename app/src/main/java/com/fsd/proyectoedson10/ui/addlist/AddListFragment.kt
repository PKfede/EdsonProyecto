package com.fsd.proyectoedson10.ui.addlist

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.ListETY

import com.fsd.proyectoedson10.R
import yuku.ambilwarna.AmbilWarnaDialog

class AddListFragment : Fragment() {

    companion object {
        fun newInstance() = AddListFragment()
    }

    private lateinit var viewModel: AddListViewModel
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_add_list_, container, false)

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

        val nameList = AppDatabase.getList()

        val db = AppDatabase.getAppDatabase(view.context)
        Stetho.initializeWithDefaults(view.context)

        val listas = db.ListDAO().getAll()

        colorButton.setOnClickListener( object: View.OnClickListener{

            override fun onClick(v : View) {
                openColorPicker(view.context)
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
            var rnds = (0..1000000).random()
            menu.add(R.id.group2,rnds,1,edName.text.toString()).setIcon(nameString).setOnMenuItemClickListener {
                nameList.setText(edName.text.toString())
                val drawerLayout = AppDatabase.getDrawer()
                drawerLayout.closeDrawers()
                background.setBackgroundColor(db.ListDAO().selectList(db.ListDAO().selectByName(edName.text.toString()).idList).listColor.toInt())
                true
            }


            var list = ListETY(db.UserDAO().getUser().id)
            list.idList = rnds.toString()
            list.listColor = defaultColor.toString()
            list.listIcon = nameString.toString()
            list.listName = edName.text.toString()
            db.ListDAO().insertList(list)
            //view.finish()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddListViewModel::class.java)
        // TODO: Use the ViewModel
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

}
