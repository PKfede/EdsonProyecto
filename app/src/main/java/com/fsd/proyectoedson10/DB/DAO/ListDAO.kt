package com.fsd.proyectoedson10.DB.DAO

import androidx.room.Dao
import androidx.room.Insert
import com.fsd.proyectoedson10.DB.Entities.ListETY

@Dao
interface ListDAO {
    @Insert
    fun insertList(list : ListETY)
}