package com.fsd.proyectoedson10.DB.DAO


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


interface UsersDAO {

    @Insert
    fun insertUser(user : UsersDAO)
}