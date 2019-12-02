package com.fsd.proyectoedson10.DB.Entities

import androidx.room.*

@Entity(tableName = "lists" ,foreignKeys =
[ForeignKey(
    entity = UsersETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE
)], indices = [Index(value = ["userId"], unique = true)]

)

data class  ListsETY(@ColumnInfo(name="userId") var userId: String){

    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "idLista") var idLista: String = ""
    @field:ColumnInfo(name = "listName") var listName: String = ""
    @field:ColumnInfo(name = "listColor") var listColor: String = ""
    @field:ColumnInfo(name = "listIcon") var listIcon: String = ""

}