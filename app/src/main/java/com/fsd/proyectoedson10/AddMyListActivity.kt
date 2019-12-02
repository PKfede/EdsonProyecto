package com.fsd.proyectoedson10

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.fsd.proyectoedson10.R
import yuku.ambilwarna.AmbilWarnaDialog
import java.util.*

class AddMyListActivity : AppCompatActivity() {
    private lateinit var colorButton : Button
    private  var defaultColor  : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_my_list)

        colorButton = findViewById(R.id.button_Color)
        defaultColor = ContextCompat.getColor(this,R.color.colorPrimary)

        colorButton.setOnClickListener( object: View.OnClickListener{

          override fun onClick(v : View) {
              openColorPicker()

          }
            })

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