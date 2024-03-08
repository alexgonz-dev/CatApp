package com.catapp.domainapp.domain.usecase

import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetDisplayableItemUseCase
import com.catapp.domainapp.domain.model.CatAppEntity
import com.catapp.domainapp.domain.model.DisplayableItemBuilder

class GetDisplayableItemUseCaseImpl : GetDisplayableItemUseCase {
    override suspend fun fetchItem(config: String, itemId: String): Result<DisplayableItem?> {
        val itemConfig = CatAppEntity.convertFromConfig(config)
        val items = DisplayableItemBuilder.build(itemConfig)
        return Result.success(items.firstOrNull { it.id == itemId })
    }
}