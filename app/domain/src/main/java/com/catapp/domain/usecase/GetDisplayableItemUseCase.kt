package com.catapp.domain.usecase

import com.catapp.domain.model.DisplayableItem

interface GetDisplayableItemUseCase {
    suspend fun fetchItem(config: String, itemId: String): Result<DisplayableItem?>
}