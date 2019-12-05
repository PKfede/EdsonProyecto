package com.fsd.proyectoedson10.ui.notification

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DemoAdapterTaskNotification
import com.fsd.proyectoedson10.R


class NotificationFragment : Fragment() {

    companion object {
        fun newInstance() =
            NotificationFragment()
    }

    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_notification, container, false)

        val db = AppDatabase.getAppDatabase(view.context)
        val listNoti = AppDatabase.getNotificationList().toTypedArray()


        var rv = view.findViewById<RecyclerView>(R.id.rv2).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@NotificationFragment.context)
            adapter = DemoAdapterTaskNotification(listNoti)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
