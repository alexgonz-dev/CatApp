package com.catapp.domainapp.domain.model

data class CatAppEntityItem(
    val _id: String,
    val createdAt: String,
    val owner: String,
    val tags: List<String>,
    val updatedAt: String
)