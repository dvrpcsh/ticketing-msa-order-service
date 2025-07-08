package com.ticketing.ticketing_poc.domain.order.dto

data class OrderCancelMessage(
    val orderId: Long,
    val productId: Long,
    val seatId: String
)
