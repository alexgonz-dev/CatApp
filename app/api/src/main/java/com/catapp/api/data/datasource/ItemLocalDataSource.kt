package com.catapp.api.data.datasource

import com.catapp.api.data.local.entity.ItemEntity

interface ItemLocalDataSource {

    suspend fun getItem(itemId: String): Result<ItemEntity?>

    suspend fun saveItem(item: ItemEntity): Result<Unit>
}