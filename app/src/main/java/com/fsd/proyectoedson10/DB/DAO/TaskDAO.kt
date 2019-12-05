package com.fsd.proyectoedson10.DB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY

@Dao
interface TaskDAO {

    @Insert
    fun insertTask(list : TaskETY)

    @Query("SELECT*FROM task")
    fun getAll(): Array<TaskETY>

    @Query("SELECT * FROM task WHERE listId = :idList")
    fun getTaskById(idList : String) : Array<TaskETY>

    @Query("SELECT * FROM task WHERE priority != '0' ORDER BY priority DESC")
    fun orderByPriority() : Array<TaskETY>

   @Query("SELECT * FROM task WHERE expiredDate != '' ORDER BY expiredDate DESC")
   fun orderByDate() : Array<TaskETY>


}