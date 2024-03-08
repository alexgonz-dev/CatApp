package com.catapp.api.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
class ItemEntity (
    @PrimaryKey
    val id: String,
    val imageData: ByteArray?
)