package com.fsd.proyectoedson10

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.*
import android.widget.*
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
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY
import com.fsd.proyectoedson10.DB.Entities.UserETY
import com.fsd.proyectoedson10.ui.addlist.AddListFragment
import com.fsd.proyectoedson10.ui.list.ListFragment
import com.fsd.proyectoedson10.ui.notification.NotificationFragment
import com.fsd.proyectoedson10.ui.sharedlist.SharedListFragment
import com.fsd.proyectoedson10.ui.task.TaskFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class DemoAdapter(private val tasks: Array<TaskETY>) :
    RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {

    class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvName: TextView
        private var imagePriority : ImageView
        private var creator : TextView
        private var date : TextView
        private var description : TextView

        init {
            tvName = view.findViewById(R.id.name)
            imagePriority = view.findViewById(R.id.iconPriority)
            creator = view.findViewById(R.id.creator)
            date = view.findViewById(R.id.date)
            description = view.findViewById(R.id.description)

        }

        val db = AppDatabase.getAppDatabase(view.context)

        public fun bind(task: TaskETY) {
            tvName.setText(task.title)
            when(task.priority){
                "0" -> {imagePriority.setImageResource(R.drawable.sin_importancia)}
                "1" -> {imagePriority.setImageResource(R.drawable.baja)}
                "2" -> {imagePriority.setImageResource(R.drawable.normal)}
                "3" -> {imagePriority.setImageResource(R.drawable.alta)}
            }
            creator.setText("${db.UserDAO().getUser().lastName}, ${db.UserDAO().getUser().name}" )
            date.setText(task.expiredDate)
            description.setText(task.description)
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
    private lateinit var imageUser : ImageView
    private lateinit var userName : TextView
    private lateinit var userEmail : TextView
    private lateinit var btnLogOut : ImageButton
    private lateinit var btnNotificacion : ImageButton

    val db = AppDatabase.getAppDatabase(this)
    val userThing = db.UserDAO().getUser().name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        val fab1 : FloatingActionButton = findViewById(R.id.fab)
        var fragment = TaskFragment()
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        fab1.isVisible = false
        fab1.isClickable = false
        AppDatabase.setCurrentListId(R.id.nav_alls)

        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)


        fillNavigationDrawer()


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivity(intent)
            finish()
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
                R.id.nav_planneds, R.id.nav_addList, R.id.nav_sharedList
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
        //db.TaskDAO().InsertChingon()

    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {


        menu.isChecked = true
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        drawerLayout.closeDrawers()
        //Log.d("Hola", menu.itemId.toString())
        fab.isVisible = true
        fab.isClickable = true
        when(menu.itemId){
            R.id.nav_addList->{
                val intent = Intent(this, AddMyListActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_planneds->{
                var fragment = TaskFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
                fab.isVisible = false
                fab.isClickable = false
                AppDatabase.setCurrentListId(R.id.nav_planneds)
            }
            R.id.nav_alls->{
                var fragment = TaskFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
                fab.isVisible = false
                fab.isClickable = false
                AppDatabase.setCurrentListId(R.id.nav_alls)
            }
            R.id.nav_importants->{
                var fragment = TaskFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
                fab.isVisible = false
                fab.isClickable = false
                AppDatabase.setCurrentListId(R.id.nav_importants)
            }
            R.id.nav_sharedList->{
                var fragment = SharedListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
            }
        }

        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        imageUser = findViewById(R.id.imageUser)
        userName = findViewById(R.id.tv_userName)
        userEmail = findViewById(R.id.tv_userEmail)
        btnLogOut = findViewById(R.id.btnLogOut)
        btnNotificacion = findViewById(R.id.notificationButton)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        btnNotificacion.setOnClickListener{
            var fragment = NotificationFragment()
            supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
            fab.isVisible = false
            fab.isClickable = false
            drawerLayout.closeDrawers()
        }

        val db  = AppDatabase.getAppDatabase(this)
        val user = db.UserDAO().getUser()

        when(user.avatar){
            "1" ->{
                imageUser.setImageResource(R.drawable.mujer_negra_rara)
            }
            "2" ->{
                imageUser.setImageResource(R.drawable.mujer_pelo_gris)
            }
            "3" ->{
                imageUser.setImageResource(R.drawable.mujer_verde)
            }
            "4" ->{
                imageUser.setImageResource(R.drawable.hombre_negro_calvo)
            }
            "5" ->{
                imageUser.setImageResource(R.drawable.hombre_pelo_gris)
            }
            "6" ->{
                imageUser.setImageResource(R.drawable.hombre_wero)
            }
        }


        userName.setText(user.name)
        userEmail.setText(user.id.replace("""[,]""".toRegex(), "."))

        btnLogOut.setOnClickListener{
            onSignOff()
        }
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

        val  fb : FloatingActionButton = findViewById(R.id.fab)

        Log.d("tamano", listLists.size.toString())
        if(listLists.isNotEmpty()) {
            for (x in listLists) {
                menu.add(R.id.group2, x.idList.toInt(), 1, x.listName)
                    .setIcon(x.listIcon.toInt()).setOnMenuItemClickListener {
                        fb.isVisible = true
                        fb.isClickable = true
                        val drawerLayout = AppDatabase.getDrawer()
                        drawerLayout.closeDrawers()

                        AppDatabase.setCurrentListId(x.idList.toInt())
                        var fragment = ListFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
                        true
                    }
            }
        }


    }

    fun onSignOff()
    {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Cerrar Sesión")
        builder.setMessage("¿Desea cerrar sesión?")

        builder.setPositiveButton("Si") { dialog, which ->

            val db = AppDatabase.getAppDatabase(this)
            if (db.UserDAO().getUser().isLogged == 1) {
                db.UserDAO().updateByNameTo0(userThing)
            }
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        builder.setNeutralButton("No") { dialog, which ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}