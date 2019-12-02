package com.fsd.proyectoedson10.DB.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index(value = ["idUser"], unique = true)]
    )

data class UsersEntity(
    @PrimaryKey @ColumnInfo(name = "idUser") val id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var answer: String,
    @ColumnInfo(name = "avatar") var avatar: String,
    @ColumnInfo(name = "status") var status : Int)