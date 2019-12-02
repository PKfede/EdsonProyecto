package com.fsd.proyectoedson10.DB.Entities
import androidx.room.*


@Entity(tableName = "notification", foreignKeys = [ForeignKey(
    entity = UserETY::class,
    parentColumns = ["idUser"],
    childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = ListETY::class,
        parentColumns = ["idList"],
        childColumns = ["listId"], onDelete = ForeignKey.CASCADE)],

    indices = [Index(value = ["userId"], unique = true), Index(value = ["listId"], unique = true)]
)
data class NotificationETY(@ColumnInfo(name="userId") var userId: Int,
                           @ColumnInfo(name="listId") var sharedListId: Int,
                           @ColumnInfo(name="date") var date: String
                           ){

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idNotification", index = true) var idNotification: Int = 0
}