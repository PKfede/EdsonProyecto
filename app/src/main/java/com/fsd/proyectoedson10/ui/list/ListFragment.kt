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
import com.fsd.proyectoedson10.DemoAdapterTaskList

import com.fsd.proyectoedson10.R
import kotlinx.android.synthetic.main.activity_list.view.*

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var currentList: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_list, container, false)

        val idList = AppDatabase.getCurrentListId()
        val db = AppDatabase.getAppDatabase(view.context)
        val listTask = db.TaskDAO().getTaskById(idList.toString())
        val background : RecyclerView = view.findViewById(R.id.rv)
        view.nameList.setText(db.ListDAO().selectList(idList.toString()).listName)
        background.setBackgroundColor(db.ListDAO().selectList(idList.toString()).listColor.toInt())
        //Log.d("hola", listTask.size.toString())

        if(listTask != null) {

            var rv = view.findViewById<RecyclerView>(R.id.rv).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@ListFragment.context)
                adapter = DemoAdapterTaskList(listTask)
            }
        }



        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
