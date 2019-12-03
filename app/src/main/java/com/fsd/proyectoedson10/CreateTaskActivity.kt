package com.fsd.proyectoedson10

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
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
                month = month + 1
                Log.d(TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")

                val date = "$month/$day/$year"
                btnDate.setText(date)  // el date es lo que vas a guardar en la BD como la fecha ejemplo del string que imprime 11/5/2019
            }

    }


}