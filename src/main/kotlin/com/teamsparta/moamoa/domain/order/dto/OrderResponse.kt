package com.teamsparta.moamoa.domain.order.dto

import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long,
    val productName: String,
    val totalPrice: Double,
    val address: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val status: String,
    val discount: Double,
    val quantity: Int,
    val orderUid: String,
)
