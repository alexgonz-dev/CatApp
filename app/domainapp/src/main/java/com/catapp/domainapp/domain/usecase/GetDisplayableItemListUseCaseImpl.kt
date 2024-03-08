package com.catapp.domainapp.domain.usecase

import com.catapp.domain.model.DisplayableItem
import com.catapp.domain.usecase.GetDisplayableItemListUseCase
import com.catapp.domainapp.domain.model.CatAppEntity
import com.catapp.domainapp.domain.model.DisplayableItemBuilder
import com.google.gson.Gson

class GetDisplayableItemListUseCaseImpl : GetDisplayableItemListUseCase {
    override suspend fun fetchItemsList(config: String): Result<List<DisplayableItem>> {
        val itemConfig = CatAppEntity.convertFromConfig(config)

        return Result.success(DisplayableItemBuilder.build(itemConfig))
    }
}