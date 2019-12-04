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
    fun getById(id : String) : UserETY?

    @Query("SELECT * FROM user")
    fun getUser() : UserETY

    @Query("SELECT * FROM user WHERE name = :name")
    fun getUserByName(name: String): UserETY?

    @Query("DELETE FROM user where idUser = :name")
    fun deleteUserById(name:String)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Query("UPDATE user SET isLogged = 0 where name = :name ")
    fun updateByNameTo0(name:String)

    @Query("UPDATE user SET isLogged = 1 where name = :name ")
    fun updateByNameTo1(name:String)

}