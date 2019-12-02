package com.fsd.proyectoedson10.DB.DAO

import androidx.room.*
import com.fsd.proyectoedson10.DB.Entities.NotificationETY

@Dao
interface NotificationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(idUser:Int, idSharedList: Int, date: String)

    @Delete
    fun deleteUser(notification: NotificationETY)

    @Query("DELETE FROM notification where idNotification= :id")
    fun deleteById(id:Int)

    @Query("SELECT*FROM notification WHERE idNotification = :id")
    fun getById(id:Int): NotificationETY

    @Query("SELECT*FROM notification")
    fun getAll(): Array<NotificationETY>


}