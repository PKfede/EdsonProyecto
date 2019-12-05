package com.fsd.proyectoedson10.DB.DAO

import androidx.room.*
import com.fsd.proyectoedson10.DB.Entities.SharedListETY

@Dao
interface SharedListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sharedListETY: SharedListETY)

    @Delete
    fun delete(sharedListETY: SharedListETY)

    @Query("DELETE FROM sharedList WHERE idSharedList = :id")
    fun deleteById(id:String)

    @Query("SELECT*FROM sharedList where idSharedList = :id")
    fun getById(id: String) : SharedListETY

    @Query("SELECT*FROM sharedList where userId = :id")
    fun getByIdUser(id: String) : List<SharedListETY>

    @Query("SELECT*FROM sharedList where listId = :id")
    fun getByIdList(id: String) : List<SharedListETY>

    @Query("SELECT DISTINCT listId, * FROM sharedList")
    fun getAllDistinctSharedList() : List<SharedListETY>

    @Query("SELECT*FROM sharedList where listId = :idList and userId = :idUser ")
    fun getByIdListAndByIdUser(idList: String, idUser:String) : List<SharedListETY>

}