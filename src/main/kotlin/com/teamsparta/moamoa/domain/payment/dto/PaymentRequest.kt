package com.teamsparta.moamoa.domain.payment.dto

data class PaymentRequest(
    val orderUid: String,
    val itemName: String,
    val buyerName: String,
    val paymentPrice: Double,
    val buyerAddress: String,
    val buyerPhone: String,
)
