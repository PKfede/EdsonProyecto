package com.fsd.proyectoedson10.DB.DAO

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.TaskETY

@Dao
interface TaskDAO {

    @Query("SELECT*FROM task")
    fun getAll(): Array<TaskETY>

    @Query("SELECT * FROM task WHERE listId = :idList")
    fun getTaskById(idList : String) : Array<TaskETY>

    @Query("INSERT INTO task (idTask, listId, title, expiredDate, priority, userId, status) VALUES ('1','210697', 'Ir por mi hermanita a las 2', '2019-12-06', 'no asignada', 'AAA', '1')")
    fun InsertChingon()

}