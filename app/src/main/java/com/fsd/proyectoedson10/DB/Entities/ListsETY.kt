package com.fsd.proyectoedson10.DB.Entities

import androidx.room.*

@Entity(tableName = "lists" ,foreignKeys =
[ForeignKey(
    entity = UsersETY::class,
    parentColumns = ["id_user"],
    childColumns = ["user_id"], onDelete = ForeignKey.CASCADE
)], indices = [Index(value = ["user_id"], unique = true)]

)

data class  ListsETY(@ColumnInfo(name="user_id") var user_id: Int){

    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id_lista") var id_lista: Int = 0




}