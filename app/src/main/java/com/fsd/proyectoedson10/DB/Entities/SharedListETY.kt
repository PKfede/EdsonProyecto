package com.fsd.proyectoedson10.DB.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "sharedList",foreignKeys =
[ForeignKey(
    entity = ListETY::class,
    parentColumns = ["idList"],
    childColumns = ["listId"], onDelete = ForeignKey.CASCADE
), ForeignKey(
    entity = UserETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE)]
)

data class SharedListETY (
    @field:ColumnInfo(name = "listId", index = true) var listId: String,
    @field:ColumnInfo(name = "userId", index = true) var userId: Int
){
    @PrimaryKey @ColumnInfo(name = "idSharedList", index = true) var idSharedList: String = ""
}