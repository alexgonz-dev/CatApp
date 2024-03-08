package com.catapp.api.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.catapp.api.data.datasource.ItemLocalDataSource
import com.catapp.api.data.datasource.ItemRemoteDataSource
import com.catapp.api.data.local.entity.ItemEntity
import com.catapp.api.data.network.ItemService
import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetDisplayableItemImageUseCase
import java.io.IOException
import javax.inject.Inject

class GetDisplayableItemImageUseCaseImpl @Inject constructor(
    private val itemLocalDataSource: ItemLocalDataSource,
    private val itemRemoteDataSource: ItemRemoteDataSource,
) : GetDisplayableItemImageUseCase {
    override suspend fun loadItemImage(item: DisplayableItem?): Result<Bitmap?> {
        if (item == null)
            return Result.success(null) //HACK: this should be a failure

        val localItem = itemLocalDataSource.getItem(item.id).getOrNull()
        var imageBytes: ByteArray? = null
        if (localItem == null) {
            imageBytes = itemRemoteDataSource.downloadImage(item.id).getOrNull()

            val itemEntity = ItemEntity(item.id, imageBytes)

            itemLocalDataSource.saveItem(itemEntity)
        } else {
            imageBytes = localItem.imageData
        }

        if (imageBytes == null)
            return Result.success(null) //HACK: this should be a failure

        return Result.success(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size))
    }
}