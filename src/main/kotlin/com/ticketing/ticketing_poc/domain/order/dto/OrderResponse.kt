package com.ticketing.ticketing_poc.domain.order.dto

import com.ticketing.ticketing_poc.domain.order.Order
import com.ticketing.ticketing_poc.domain.order.OrderStatus
import java.time.LocalDateTime

/**
 * 주문 생성 후 클라이언트에게 응답할 때 사용하는 DTO
 */
data class OrderResponse(
    val orderId: Long,
    val userId: Long,
    val productId: Long,
    val orderStatus: OrderStatus,
    val orderedAt: LocalDateTime
) {
    companion object {
        fun from(order: Order): OrderResponse {
            return OrderResponse(
                orderId = order.id!!,
                userId = order.userId,
                productId = order.productId,
                orderStatus = order.status,
                orderedAt = order.createdAt
            )
        }
    }
}
