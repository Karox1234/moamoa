package com.teamsparta.moamoa.domain.order.dto

import jakarta.validation.constraints.NotNull

data class UpdateOrderRequest(
    @field:NotNull
    val address: String,
)