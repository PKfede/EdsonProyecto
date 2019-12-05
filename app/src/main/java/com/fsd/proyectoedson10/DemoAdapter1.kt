package com.fsd.proyectoedson10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.NotificationETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY

internal class DemoAdapter1(private val notifications: Array<NotificationETY>) :
    RecyclerView.Adapter<DemoAdapter1.DemoViewHolder>() {

    internal class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        init {


        }

        val db = AppDatabase.getAppDatabase(view.context)

        public fun bind(notifcation: NotificationETY) {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoAdapter1.DemoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_demo_holder, parent, false) as View

        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount() = notifications.size
}