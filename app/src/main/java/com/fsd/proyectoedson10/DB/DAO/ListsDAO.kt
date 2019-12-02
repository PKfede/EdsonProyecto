package com.fsd.proyectoedson10.DB.DAO

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ListsDAO {
    @Insert
    fun insertList(lists : ListsDAO)
}