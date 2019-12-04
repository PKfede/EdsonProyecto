package com.fsd.proyectoedson10.DB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fsd.proyectoedson10.DB.Entities.ListETY
import com.fsd.proyectoedson10.DB.Entities.NotificationETY
import java.util.*

@Dao
interface ListDAO {
    @Insert
    fun insertList(list : ListETY)

    @Query ("SELECT * FROM list WHERE idList = :idList")
    fun selectList(idList : Int) : ListETY

    @Query("SELECT*FROM list")
    fun getAll(): Array<ListETY>

    @Query("SELECT * FROM list WHERE listName = :name")
    fun selectByName(name : String) :ListETY

    @Query ("SELECT * FROM list WHERE userId = :userId")
    fun selectByUser(userId : String) :List<ListETY>

    @Query("SELECT idList FROM list")
    fun selectIds() : List<String>
}