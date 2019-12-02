package com.fsd.proyectoedson10.DB.DAO


import androidx.room.Dao;
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDAO {

    @Insert
    fun insertUser(user : UsersDAO)

    @Query("Select * from users")
    fun getAll() : List<UsersDAO>

    @Query("Select * from users where idUser = :id")
    fun getById(id : String) : UsersDAO
}