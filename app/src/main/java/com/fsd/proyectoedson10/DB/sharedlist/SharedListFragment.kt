package com.fsd.proyectoedson10.DB.sharedlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DemoAdapterTaskNotification

import com.fsd.proyectoedson10.R

class SharedListFragment : Fragment() {

    companion object {
        fun newInstance() = SharedListFragment()
    }

    private lateinit var viewModel: SharedListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_shared_list, container, false)



        var rv = view.findViewById<RecyclerView>(R.id.rv3).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SharedListFragment.context)
            //adapter = DemoAdapterTaskNotification()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SharedListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
