package com.catapp.domain.model

data class DisplayableItem(
    val id: String,
    val createdAt: String,
    val owner: String,
    val tags: List<String>,
    val updatedAt: String
)