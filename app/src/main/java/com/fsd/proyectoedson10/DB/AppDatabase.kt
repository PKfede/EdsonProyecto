package com.fsd.proyectoedson10.DB


import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase


//@Database(
//    entities = [
//        Perfil::class, Pregunta::class, Puntuacion::class, Juego::class
//    ], version = 1
//)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun getperfilDao(): PerfilDao
//
//    abstract fun getpreguntaDao(): PreguntaDao
//
//    abstract fun getpuntuacionDao(): PuntuacionDao
//
//    abstract fun getjuegoDao(): JuegoDao

    //abstract fun professorCategoryDao(): ProfessorCategoryDao

    //abstract fun professorDao(): ProfessorDao

    //abstract fun reportsDao(): ReportsDao

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