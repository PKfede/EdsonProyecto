package com.fsd.proyectoedson10

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.TaskETY

internal class DemoAdapterTaskList(private val tasks: Array<TaskETY>) :
    RecyclerView.Adapter<DemoAdapterTaskList.DemoViewHolder>() {

    internal class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvName: TextView
        private var imagePriority: ImageView
        private var creator: TextView
        private var date: TextView
        private var description: TextView
        private var cbTask : CheckBox

        init {
            tvName = view.findViewById(R.id.name)
            imagePriority = view.findViewById(R.id.iconPriority)
            creator = view.findViewById(R.id.creator)
            date = view.findViewById(R.id.date)
            description = view.findViewById(R.id.description)
            cbTask = view.findViewById(R.id.cbTask)

        }

        val db = AppDatabase.getAppDatabase(view.context)
        val builder = AlertDialog.Builder(view.context)

        public fun bind(task: TaskETY) {
            tvName.setText(task.title)
            when (task.priority) {
                "0" -> {
                    imagePriority.setImageResource(R.drawable.sin_importancia)
                }
                "1" -> {
                    imagePriority.setImageResource(R.drawable.baja)
                }
                "2" -> {
                    imagePriority.setImageResource(R.drawable.normal)
                }
                "3" -> {
                    imagePriority.setImageResource(R.drawable.alta)
                }
            }
            creator.setText("${db.UserDAO().getUser().lastName}, ${db.UserDAO().getUser().name}")
            date.setText(task.expiredDate)
            description.setText(task.description)
            
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoAdapterTaskList.DemoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_demo_holder, parent, false) as View

        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

}
