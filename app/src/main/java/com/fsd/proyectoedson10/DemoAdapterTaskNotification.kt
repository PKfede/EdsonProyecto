package com.fsd.proyectoedson10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.NotificationETY
import com.fsd.proyectoedson10.DB.Network

internal class DemoAdapterTaskNotification(private val notifications: Array<NotificationETY>) :
    RecyclerView.Adapter<DemoAdapterTaskNotification.DemoViewHolder>() {

    internal class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleList : TextView
        val creatorNotification : TextView
        val notiDate : TextView
        val buttonAceptar : Button
        val buttonCancel : Button

        init {
            titleList = view.findViewById(R.id.notification)
            creatorNotification = view.findViewById(R.id.creatorNotification)
            notiDate = view.findViewById(R.id.notificationDate)
            buttonAceptar = view.findViewById(R.id.buttonAcept)
            buttonCancel = view.findViewById(R.id.buttonCancel)
        }


        val db = AppDatabase.getAppDatabase(view.context)
        var context =  view.context

        public fun bind(notification: NotificationETY)
        {
            titleList.setText(notification.listName)
            creatorNotification.setText("${notification.sender} te ha invitado a una nueva lista compartida.")
            notiDate.setText(notification.date)

            buttonCancel.setOnClickListener{
                db.NotificationDAO().deleteById(notification.idNotification)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoAdapterTaskNotification.DemoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_notification_holder, parent, false) as View

        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount() = notifications.size
}