package com.ticketing.ticketing_poc.domain.order.dto

/**
 * 주문 생성 시 Kafka로 발행(Produce)할 메시지의 데이터 모델
 */
data class OrderCreationMessage (
    val orderId: Long,
    val userId: Long,
    val productId: Long
)