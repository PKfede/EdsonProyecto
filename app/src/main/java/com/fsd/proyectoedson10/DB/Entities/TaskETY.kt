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

    indices = [Index(value = ["userId"], unique = false), Index(value = ["listId"], unique = false)]
)
data class TaskETY(
    @PrimaryKey @ColumnInfo(name = "idTask", index = true) var idTask: String = "",
    @field:ColumnInfo(name="listId") var listId : String,
    @field:ColumnInfo(name="title") var title: String,
    @field:ColumnInfo(name="expiredDate") var expiredDate: String,
    @field:ColumnInfo(name="priority") var priority: String,
    @field:ColumnInfo(name="userId") var userId: String,
    @field:ColumnInfo(name="status") var status: String
)