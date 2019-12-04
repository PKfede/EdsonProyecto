package com.fsd.proyectoedson10.ui.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DemoAdapter

import com.fsd.proyectoedson10.R

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_list, container, false)
        return view

        val db = AppDatabase.getAppDatabase(view.context)
        val listTask = db.TaskDAO().getTaskById("210697")


        var rv = view.findViewById<RecyclerView>(R.id.rv).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context)
            adapter = DemoAdapter(listTask)
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
