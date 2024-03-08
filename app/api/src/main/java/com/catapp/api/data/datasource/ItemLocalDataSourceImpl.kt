package com.catapp.api.data.datasource

import com.catapp.api.data.local.dao.ItemDao
import com.catapp.api.data.local.entity.ItemEntity
import javax.inject.Inject

class ItemLocalDataSourceImpl @Inject constructor(
    private val itemDao: ItemDao
) : ItemLocalDataSource {
    override suspend fun getItem(itemId: String): Result<ItemEntity?> {
        return try {
            val task = itemDao.getItemById(itemId)
            Result.success(task)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun saveItem(item: ItemEntity): Result<Unit> {
        itemDao.saveItem(item)
        return Result.success(Unit)
    }
}