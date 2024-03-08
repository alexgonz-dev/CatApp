package com.catapp.domainapp.domain.model

import com.catapp.domain.model.DisplayableItem

class DisplayableItemBuilder {
    companion object {
        fun build(entity: CatAppEntity?): List<DisplayableItem> {
            val list: MutableList<DisplayableItem> = mutableListOf()
            entity?.forEach {
                list.add(build(it))
            }
            return list
        }

        private fun build(item: CatAppEntityItem): DisplayableItem {
            with(item) {
                return DisplayableItem(_id, createdAt, owner, tags, updatedAt)
            }
        }
    }
}