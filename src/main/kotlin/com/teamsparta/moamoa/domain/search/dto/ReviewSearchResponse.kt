package com.teamsparta.moamoa.domain.search.dto

data class ReviewSearchResponse(
    val reviewId: Long,
    val productId: Long,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val name: String,
    val likes: Int,
)
