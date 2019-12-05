package com.fsd.proyectoedson10.ui.task

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.TaskETY
import com.fsd.proyectoedson10.DemoAdapterTaskList

import com.fsd.proyectoedson10.R

class TaskFragment : Fragment() {

    companion object {
        fun newInstance() = TaskFragment()
    }

    private lateinit var viewModel: TaskViewModel
    private lateinit var listTask : Array<TaskETY>
    private lateinit var orderButtom : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_task, container, false)
        val nameList1 : TextView = view.findViewById(R.id.nameList1)
        val db = AppDatabase.getAppDatabase(view.context)
        orderButtom = view.findViewById(R.id.orderButton)

        when(AppDatabase.getCurrentListId())
        {
            R.id.nav_importants ->{
                nameList1.setText("Importantes")
                listTask = db.TaskDAO().orderByPriority()
                orderButtom.setImageResource(R.drawable.priorityhigh)
            }

            R.id.nav_alls ->{
                nameList1.setText("Todas")
                listTask = db.TaskDAO().getAll()
                orderButtom.setImageResource(R.drawable.todos)

            }

            R.id.nav_planneds -> {
                nameList1.setText("Planeadas")
                listTask = db.TaskDAO().orderByDate()
                orderButtom.setImageResource(R.drawable.date)
            }
        }


        var rv = view.findViewById<RecyclerView>(R.id.rv1).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@TaskFragment.context)
            adapter = DemoAdapterTaskList(listTask)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
