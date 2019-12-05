package com.fsd.proyectoedson10.DB.Entities
import androidx.room.*

@Entity(tableName = "notification")


data class NotificationETY(@ColumnInfo(name="userId") var userId: String,
                           @ColumnInfo(name="listId") var sharedListId: String,
                           @ColumnInfo(name="date") var date: String,
                           @ColumnInfo(name="Sender") var sender : String,
                           @ColumnInfo(name="listName") var listName : String
){

   @PrimaryKey @ColumnInfo(name = "idNotification", index = true) var idNotification: String = ""
}