package com.fsd.proyectoedson10.DB


import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fsd.proyectoedson10.DB.DAO.*
import com.fsd.proyectoedson10.DB.Entities.*
import com.fsd.proyectoedson10.R
import com.google.android.material.navigation.NavigationView


@Database(
    entities = [
        UserETY::class, SharedListETY::class, TaskETY::class, NotificationETY::class, ListETY::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun UserDAO(): UserDAO
//
    abstract fun TaskDAO(): TaskDAO

    abstract fun SharedListDAO(): SharedListDAO
//
    abstract fun NotificationDAO(): NotificationDAO

    abstract fun ListDAO(): ListDAO


    companion object {

        private var INSTANCE: AppDatabase? = null
        private lateinit var navView : NavigationView
        private lateinit var nameList : TextView
        private lateinit var drawerLayout: DrawerLayout
        private lateinit var backgorund : LinearLayout

        fun getNav() = navView
        fun setNav(naView : NavigationView){
            navView = naView
        }

        fun getList() = nameList
        fun setList(NameList : TextView){
            nameList = NameList
        }

        fun getDrawer() = drawerLayout
        fun setDrawer(DrawerLayout : DrawerLayout)
        {
            drawerLayout = DrawerLayout
        }

        fun getBackground() = backgorund
        fun setBackground(bg : LinearLayout)
        {
            backgorund = bg
        }
        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java,
                    "listas.db"
                )
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            initializeData(db)
                        }
                    })
                    .build()
            }

            return INSTANCE as AppDatabase
        }

        fun initializeData(db: SupportSQLiteDatabase) {

            //db.execSQL("INSERT INTO user (idUser,name, lastName, password, avatar, status) VALUES ('AAA', 'Alex', 'Brito', '111', 'avatar1', 1)")
            //db.beginTransaction();

//            db.execSQL("INSERT INTO perfil (iniciales, avatar, categorias, numpreguntas, dificultad, pistas, numpistas, estado) VALUES ('AAA',3,'100101',5,2,0,0,1)")


//



            //db.setTransactionSuccessful();
            //db.endTransaction();
        }
    }
}