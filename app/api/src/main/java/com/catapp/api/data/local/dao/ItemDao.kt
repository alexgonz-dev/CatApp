package com.catapp.api.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.catapp.api.data.local.entity.ItemEntity

@Dao
interface  ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(task: ItemEntity)

    @Query("SELECT * FROM items WHERE id = :itemId")
    suspend fun getItemById(itemId: String): ItemEntity?
}