package com.fsd.proyectoedson10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.NotificationETY

internal class DemoAdapterTaskNotification(private val notifications: Array<NotificationETY>) :
    RecyclerView.Adapter<DemoAdapterTaskNotification.DemoViewHolder>() {

    internal class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleList : TextView
        val creatorNotification : TextView
        val notiDate : TextView


        init {

            titleList = view.findViewById(R.id.notificationTitle)
            creatorNotification = view.findViewById(R.id.creatorNotification)
            notiDate = view.findViewById(R.id.notificationDate)
        }

        val db = AppDatabase.getAppDatabase(view.context)



        public fun bind(notification: NotificationETY)
        {
            titleList.setText(db.ListDAO().selectList(notification.sharedListId).listName)
            creatorNotification.setText("${db.UserDAO().getById(notification.userId)?.name} " +
                    "${db.UserDAO().getById(notification.userId)?.lastName} te ha invitado a una nueva lista compartida.")
            notiDate.setText(notification.date)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoAdapterTaskNotification.DemoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_demo_holder, parent, false) as View

        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount() = notifications.size
}