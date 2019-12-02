package com.fsd.proyectoedson10.DB.DAO


import androidx.room.Dao;
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fsd.proyectoedson10.DB.Entities.UserETY

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user : UserETY): Long

    @Query("Select * from user")
    fun getAll() : List<UserETY>

    @Query("Select * from user where idUser = :id")
    fun getById(id : String) : UserETY


}