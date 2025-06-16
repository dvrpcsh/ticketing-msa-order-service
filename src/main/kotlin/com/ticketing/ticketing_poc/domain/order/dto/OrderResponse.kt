package com.ticketing.ticketing_poc.domain.order.dto

import com.ticketing.ticketing_poc.domain.order.Order
import com.ticketing.ticketing_poc.domain.order.OrderStatus
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 주문 생성 후 클라이언트에게 응답할 때 사용하는 DTO
 */
@Schema(description = "주문 정보 응답 데이터 모델")
data class OrderResponse(
    val orderId: Long,
    val userId: Long,
    val productTitle: String,
    val orderStatus: OrderStatus,
    val orderedAt: LocalDateTime
) {
    companion object {
        fun from(order: Order): OrderResponse {
            return OrderResponse(
                orderId = order.id!!,
                userId = order.userId,
                productTitle = order.product.title,
                orderStatus = order.status,
                orderedAt = order.createdAt
            )
        }
    }
}
