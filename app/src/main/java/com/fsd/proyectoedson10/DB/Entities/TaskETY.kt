package com.fsd.proyectoedson10.DB.Entities

import androidx.room.*

@Entity(tableName = "notification", foreignKeys = [ForeignKey(
    entity = UsersETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = ListsETY::class,
        parentColumns = ["idList"],
        childColumns = ["listId"], onDelete = ForeignKey.CASCADE)],

    indices = [Index(value = ["userId"], unique = true), Index(value = ["listId"], unique = true)]
)
data class TaskETY(@ColumnInfo(name="idTask") var userId: Int,
                           @ColumnInfo(name="listId") var listId : Int,
                           @ColumnInfo(name="title") var title: String,
                           @ColumnInfo(name="expiredDate") var expiredDate: String,
                           @ColumnInfo(name="priority") var priority: String,
                           @ColumnInfo(name="status") var status: String
){
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idTask", index = true) var idTask: Int = 0
}