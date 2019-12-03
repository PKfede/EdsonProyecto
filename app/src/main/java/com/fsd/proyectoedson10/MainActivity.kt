package com.fsd.proyectoedson10

import android.content.Intent
import android.os.Bundle
import android.text.Layout
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
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.facebook.stetho.Stetho
import com.fsd.proyectoedson10.DB.AppDatabase
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.UserETY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Stetho.initializeWithDefaults(this)



        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
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
                R.id.nav_planneds,R.id.nav_createTask
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




        navView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()

            val nameList : TextView = findViewById(R.id.nameList)
            AppDatabase.setList(nameList)

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.nav_alls -> {
                    nameList.setText("Todas")
                }
                R.id.nav_importants -> {
                    nameList.setText("Importantes")
                }
                R.id.nav_planneds -> {
                    nameList.setText("Planeadas")
                }
                R.id.nav_addList ->{
                    val intent = Intent(this, AddMyListActivity::class.java)
                    startActivity(intent)
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            val background : LinearLayout = findViewById(R.id.background)
            AppDatabase.setBackground(background)

            true }

            fillNavigationDrawer()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
        val listIds : List<Int> = db.ListDAO().selectIds() //Esto consigue los ids de las listas porque no necesariamente son seguidos


        if(db.ListDAO().getAll().size > 0) {
            for (x in 0..db.ListDAO().getAll().size) {
                menu.add(R.id.group2, Menu.NONE, 1, listLists[listIds[x]].listName)
                    .setIcon(listLists[listIds[x]].listIcon.toInt()).setOnMenuItemClickListener {
                    val nameList: TextView = findViewById(R.id.nameList)
                    AppDatabase.setList(nameList)
                    nameList.setText(listLists[listIds[x]].listName)
                    val drawerLayout = AppDatabase.getDrawer()
                    drawerLayout.closeDrawers()
                    background.setBackgroundColor(
                        db.ListDAO().selectList(
                            db.ListDAO().selectByName(
                                listLists[listIds[x]].listName
                            ).idList
                        ).listColor.toInt()
                    )
                    true
                }
            }
        }
    }
}
