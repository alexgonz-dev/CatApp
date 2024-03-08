package com.catapp.api.data.datasource

import android.util.Log
import com.catapp.api.data.network.ItemService
import java.io.IOException
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(
    private val itemApiService: ItemService
) : ItemRemoteDataSource {
    override suspend fun downloadImage(id: String): Result<ByteArray?> {
        return try {
            val response = itemApiService.getItem(id)
            Result.success((response.bytes()))
        } catch (e: IOException) {
            Log.e("", e.message, e)
            Result.failure(e)
            // Handle network failure
        }
    }
}