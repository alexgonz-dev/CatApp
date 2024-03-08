package com.catapp.api.domain.usecase

import android.content.Context
import android.util.Log
import com.catapp.api.R
import com.catapp.api.data.datasource.ItemLocalDataSource
import com.catapp.api.data.datasource.ItemRemoteDataSource
import com.catapp.api.data.local.entity.ItemEntity
import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetDisplayableItemImageUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetDisplayableItemImageUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val itemLocalDataSource: ItemLocalDataSource,
    private val itemRemoteDataSource: ItemRemoteDataSource,
) : GetDisplayableItemImageUseCase {
    override suspend fun loadItemImage(item: DisplayableItem?): Result<ByteArray?> {
        if (item == null)
            return Result.success(null)

        val localItem = itemLocalDataSource.getItem(item.id).getOrNull()

        Log.d(
            context.getString(R.string.getdisplayableitemimageusecase_Tag),
            context.getString(R.string.item_id_log) + item.id
        )
        var imageBytes: ByteArray? = null
        if (localItem?.imageData == null) {
            Log.d(
                context.getString(R.string.getdisplayableitemimageusecase_Tag), context.getString(
                    R.string.notfound_log
                )
            )
            itemRemoteDataSource.downloadImage(item.id).fold(
                onSuccess = { data ->
                    Log.d(
                        context.getString(R.string.getdisplayableitemimageusecase_Tag),
                        context.getString(
                            R.string.processed_log
                        )
                    )
                    imageBytes = data
                },
                onFailure = { exception ->
                    Log.e(
                        context.getString(R.string.getdisplayableitemimageusecase_Tag),
                        exception.message,
                        exception
                    )
                }
            )


            val itemEntity = ItemEntity(item.id, imageBytes)

            itemLocalDataSource.saveItem(itemEntity)
        } else {
            Log.d(
                context.getString(R.string.getdisplayableitemimageusecase_Tag), context.getString(
                    R.string.found_log
                )
            )
            imageBytes = localItem.imageData
        }

        if (imageBytes == null)
            return Result.success(null)
        return Result.success(imageBytes)
    }
}