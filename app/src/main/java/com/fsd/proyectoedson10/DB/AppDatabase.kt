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
import com.fsd.proyectoedson10.ui.addsharedlist.SharedListNotification
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

        private var currentListId : Int = 0
        private var listNotification : MutableList<NotificationETY> = mutableListOf()

        fun getCurrentListId() = currentListId
        fun setCurrentListId(id:Int){
            currentListId = id
        }

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

        fun getNotificationList() = listNotification
        fun setNotificationList(listNoti: MutableList<NotificationETY>)
        {
            listNotification = listNoti
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
//
//            db.execSQL("INSERT INTO task (listId, title, expiredData, priority, userId, status) VALUES ('857428', 'Ir por mi hermanita a las 2', '2019-12-06', 'no asignada', 'AAA', 1)")
//            db.execSQL("INSERT INTO task (listId, title, expiredData, priority, userId, status) VALUES ('857428', 'Ir por la comida', '2019-12-08', 'no asignada', 'AAA', 1)")
//            db.execSQL("INSERT INTO task (listId, title, expiredData, priority, userId, status) VALUES ('857428', 'Jugar halo reach', '2019-12-09', 'no asignada', 'AAA', 1)")
//            db.execSQL("INSERT INTO task (listId, title, expiredData, priority, userId, status) VALUES ('26210', 'Jalarmela', '2019-12-09', 'no asignada', 'AAA', 1)")
//            db.execSQL("INSERT INTO task (listId, title, expiredData, priority, userId, status) VALUES ('26210', 'Hacer mis vistas', '2019-12-10', 'no asignada', 'AAA', 1)")

            //db.execSQL("INSERT INTO user (idUser,name, lastName, password, avatar, status) VALUES ('AAA', 'Alex', 'Brito', '111', 'avatar1', 1)")
            //db.beginTransaction();

//            db.execSQL("INSERT INTO perfil (iniciales, avatar, categorias, numpreguntas, dificultad, pistas, numpistas, estado) VALUES ('AAA',3,'100101',5,2,0,0,1)")


//



            //db.setTransactionSuccessful();
            //db.endTransaction();
        }
    }
}