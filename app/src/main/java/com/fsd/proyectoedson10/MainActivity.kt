package com.fsd.proyectoedson10

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY
import com.fsd.proyectoedson10.DB.Entities.UserETY
import com.fsd.proyectoedson10.ui.addlist.AddListFragment
import com.fsd.proyectoedson10.ui.gallery.GalleryFragment
import com.fsd.proyectoedson10.ui.list.ListFragment
import com.fsd.proyectoedson10.ui.send.SendFragment
import com.fsd.proyectoedson10.ui.share.ShareFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class DemoAdapter(private val tasks: Array<TaskETY>) :
    RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {

    class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvName: TextView

        init {
            tvName = view.findViewById(R.id.name)

        }

        public fun bind(task: TaskETY) {
            tvName.setText(task.title)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DemoAdapter.DemoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_demo_holder, parent, false) as View

        return DemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size
}


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        fillNavigationDrawer()

        Stetho.initializeWithDefaults(this)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        AppDatabase.setNav(navView)
        AppDatabase.setDrawer(drawerLayout)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(

                R.id.nav_home, R.id.nav_alls, R.id.nav_importants,
                R.id.nav_planneds
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        val db = AppDatabase.getAppDatabase(this)
        //db.TaskDAO().InsertChingon()

    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {

        menu.isChecked = true
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawers()
        Log.d("Hola", menu.itemId.toString())
        when(menu.itemId){
            R.id.nav_addList->{
                val intent = Intent(this, AddMyListActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_planneds->{
                var fragment = SendFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
            }
            R.id.nav_alls->{
                var fragment = ShareFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
            }
            R.id.nav_importants->{
                var fragment = GalleryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
            }
        }

        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun fillNavigationDrawer()
    {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val db = AppDatabase.getAppDatabase(this)

        var menu = navView.menu
        val listLists : List<ListETY> = db.ListDAO().selectByUser(db.UserDAO().getUser().id) // Esto consigue la lista de listas del usuario que se encuentra logeado

        if(listLists.isNotEmpty()) {
            for (x in listLists) {

                Log.d("Hola", x.idList)

                menu.add(R.id.group2, x.idList.toInt(), 1, x.listName)
                    .setIcon(x.listIcon.toInt()).setOnMenuItemClickListener {
                        val drawerLayout = AppDatabase.getDrawer()
                        drawerLayout.closeDrawers()

//                        val listTask = db.TaskDAO().getTaskById("210697")
//                        Log.d("Hola", listTask.size.toString())

//                        rv = findViewById<RecyclerView>(R.id.rv).apply {
//                            setHasFixedSize(true)
//                            layoutManager = LinearLayoutManager(this@MainActivity)
//                            adapter = DemoAdapter(listTask)
//                        }
                        AppDatabase.setCurrentListId(x.idList.toInt())
                        var fragment = ListFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
                        true
                    }
            }
        }
    }
}