package com.fsd.proyectoedson10.DB.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

    @Entity(tableName = "user", indices = [Index(value = ["idUser"])]
    )

    data class UserETY(
        @PrimaryKey @ColumnInfo(name = "idUser") val id: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "lastName") var lastName: String,
        @ColumnInfo(name = "password") var password: String,
        @ColumnInfo(name = "avatar") var avatar: String,
        @ColumnInfo(name = "status") var status : Int = 0,
        @ColumnInfo(name = "isLogged") var isLogged : Int,
        @ColumnInfo(name = "lastVisited") var lastVisited : Int = 0)

