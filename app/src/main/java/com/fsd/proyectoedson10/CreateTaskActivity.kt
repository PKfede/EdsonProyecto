package com.fsd.proyectoedson10

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.TaskETY
import java.util.*

class CreateTaskActivity : AppCompatActivity() {

    private lateinit var TAG: String
    private lateinit var btnDate: Button
    private lateinit var dateListener: DatePickerDialog.OnDateSetListener
    private lateinit var btnSaveTask : Button
    private lateinit var etDescripton : EditText
    private lateinit var etTaskTitle : EditText
    private lateinit var radioNormal : RadioButton
    private lateinit var radioHigh : RadioButton
    private lateinit var radioLow : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_task_activity)


        TAG = "CreateTask"
        btnDate = findViewById(R.id.btnDate)
        btnSaveTask = findViewById(R.id.btnSave)
        etDescripton = findViewById(R.id.editTextDescription)
        etTaskTitle = findViewById(R.id.editTextTitle)
        radioNormal = findViewById(R.id.radioNormal)
        radioHigh = findViewById(R.id.radioHigh)
        radioLow = findViewById(R.id.radioLow)
        var priority : String

        btnDate.setOnClickListener(View.OnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                this@CreateTaskActivity,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateListener,
                year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })


        dateListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var month = month
                var day = day
                var dia = day.toString()
                var mes = month.toString()
                month = month + 1
                Log.d(TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")

                if(month < 10)
                {
                    mes = "0" + mes
                }

                if(day < 10)
                {
                  dia = "0" + dia
                }

                val date = "$year/$mes/$dia"
                btnDate.setText(date)  // el date es lo que vas a guardar en la BD como la fecha ejemplo del string que imprime 11/5/2019
            }




    //    if (etTaskTitle.text.toString().trim().length > 0) {
            btnSaveTask.setOnClickListener {
                val db = AppDatabase.getAppDatabase(this)
                var deit : String

                if (radioHigh.isChecked) {
                    priority = "3"
                } else if (radioNormal.isChecked) {
                    priority = "2"
                } else if (radioLow.isChecked) {
                    priority = "1"
                } else {
                    priority = "0"
                }
                if (btnDate.text.toString() == "Fecha de vencimiento") {
                    deit = ""
                }else {
                    deit = btnDate.text.toString()
                }
                var rnds = (0..1000000).random()

                var task = TaskETY(
                    rnds.toString(),
                    AppDatabase.getCurrentListId().toString(),
                    etTaskTitle.text.toString(),
                    deit,
                    priority,
                    db.UserDAO().getUser().id,
                    "1",
                    etDescripton.text.toString()
                )

                if (etTaskTitle.text.toString().trim().length > 0) {
                    db.TaskDAO().insertTask(task)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(
                this@CreateTaskActivity,
                "Debe asignarle un titulo",
                Toast.LENGTH_SHORT
            ).show()
            etTaskTitle.setHintTextColor(Color.RED)
                }


            }
    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this@CreateTaskActivity)

        builder.setTitle("ADVERTENCIA")
        builder.setMessage("¿Estas seguro que desas cancelar la creacion de la tarea? ")

        builder.setPositiveButton("Si") { dialog, which ->

            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


}