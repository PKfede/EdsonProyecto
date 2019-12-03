package com.fsd.proyectoedson10.DB.Entities

import androidx.room.*

@Entity(tableName = "list" ,foreignKeys =
[ForeignKey(
    entity = UserETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE
)], indices = [Index(value = ["userId"], unique = false)]

)

data class  ListETY(@ColumnInfo(name="userId") var userId: String){

    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "idList") var idList: Int = 0
    @field:ColumnInfo(name = "listName") var listName: String = ""
    @field:ColumnInfo(name = "listColor") var listColor: String = ""
    @field:ColumnInfo(name = "listIcon") var listIcon: String = ""

}