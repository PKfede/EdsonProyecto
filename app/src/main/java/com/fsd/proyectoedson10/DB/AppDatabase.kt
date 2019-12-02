package com.fsd.proyectoedson10.DB


import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fsd.proyectoedson10.DB.DAO.*
import com.fsd.proyectoedson10.DB.Entities.*


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
            //db.beginTransaction();

//            db.execSQL("INSERT INTO perfil (iniciales, avatar, categorias, numpreguntas, dificultad, pistas, numpistas, estado) VALUES ('AAA',3,'100101',5,2,0,0,1)")


//



            //db.setTransactionSuccessful();
            //db.endTransaction();
        }
    }
}