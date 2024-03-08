package com.catapp.api.data.datasource

import android.graphics.Bitmap

interface ItemRemoteDataSource {
    suspend fun downloadImage(id: String): Result<ByteArray?>
}