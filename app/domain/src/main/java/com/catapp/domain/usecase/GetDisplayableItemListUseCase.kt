package com.catapp.domain.usecase

import com.catapp.domain.model.DisplayableItem

interface GetDisplayableItemListUseCase {
    suspend fun fetchItemsList(config: String): Result<List<DisplayableItem>>
}