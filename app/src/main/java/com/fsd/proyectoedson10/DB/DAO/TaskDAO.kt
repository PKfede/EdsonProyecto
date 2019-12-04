package com.fsd.proyectoedson10.DB.DAO

import androidx.room.Dao
import androidx.room.Query
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY

@Dao
interface TaskDAO {

    @Query("SELECT*FROM task")
    fun getAll(): Array<TaskETY>

    @Query("SELECT * FROM task WHERE listId = :idList")
    fun getTaskById(idList : String) : Array<TaskETY>

}