package com.teamsparta.moamoa.domain.search.dto

data class ProductSearchResponse(
    val productId: Long,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val price: Double,
    val ratingAverage: Double?,
    val likes: Int,
)
