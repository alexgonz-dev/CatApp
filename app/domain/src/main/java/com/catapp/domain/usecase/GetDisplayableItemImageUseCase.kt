package com.catapp.domain.usecase

import com.catapp.domain.model.DisplayableItem

interface GetDisplayableItemImageUseCase {
    suspend fun loadItemImage(item: DisplayableItem?): Result<ByteArray?>
}