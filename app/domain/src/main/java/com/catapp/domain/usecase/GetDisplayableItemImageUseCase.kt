package com.catapp.domain.usecase

import android.graphics.Bitmap
import com.catapp.domain.model.DisplayableItem

interface GetDisplayableItemImageUseCase {
    suspend fun loadItemImage(item: DisplayableItem?): Result<Bitmap?>
}