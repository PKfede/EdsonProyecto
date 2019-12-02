package com.fsd.proyectoedson10.DB.Entities
import androidx.room.*


@Entity(tableName = "user_configuration", foreignKeys = [ForeignKey(
    entity = UsersEntityETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = SharedListsETY::class,
        parentColumns = ["idUser"],
        childColumns = ["userId"], onDelete = ForeignKey.CASCADE)],

    indices = [Index(value = ["userId"], unique = true), Index(value = ["sharedListId"], unique = true)]
)
data class NotificationETY(@ColumnInfo(name="userId") var userId: Int, @ColumnInfo(name="sharedListId") var sharedListId: Int) {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idNotification", index = true) var idNotification: Int = 0
}