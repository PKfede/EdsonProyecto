package com.fsd.proyectoedson10.DB.Entities

import androidx.room.*

@Entity(tableName = "task", foreignKeys = [ForeignKey(
    entity = UserETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = ListETY::class,
        parentColumns = ["idList"],
        childColumns = ["listId"], onDelete = ForeignKey.CASCADE)],

    indices = [Index(value = ["userId"], unique = true), Index(value = ["listId"], unique = true)]
)
data class TaskETY(
                           @ColumnInfo(name="listId") var listId : String,
                           @ColumnInfo(name="title") var title: String,
                           @ColumnInfo(name="expiredDate") var expiredDate: String,
                           @ColumnInfo(name="priority") var priority: String,
                           @ColumnInfo(name="userId") var userId: String,
                           @ColumnInfo(name="status") var status: String
){
    @PrimaryKey @ColumnInfo(name = "idTask", index = true) var idTask: String = ""
}