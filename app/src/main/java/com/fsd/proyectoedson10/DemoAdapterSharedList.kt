package com.fsd.proyectoedson10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.SharedListETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY

internal class DemoAdapterSharedList(private val shared: Array<SharedListETY>) :
    RecyclerView.Adapter<DemoAdapterSharedList.DemoViewHolder>() {

    internal class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvName: TextView
        private var imagePriority: ImageView
        private var creator: TextView
        private var date: TextView
        private var description: TextView

        init {
            tvName = view.findViewById(R.id.name)
            imagePriority = view.findViewById(R.id.iconPriority)
            creator = view.findViewById(R.id.creator)
            date = view.findViewById(R.id.date)
            description = view.findViewById(R.id.description)

        }

        val db = AppDatabase.getAppDatabase(view.context)

        public fun bind(shared: SharedListETY) {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoAdapterSharedList.DemoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_demo_holder, parent, false) as View

        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(shared[position])
    }

    override fun getItemCount() = shared.size

}