package com.ticketing.ticketing_poc.domain.order.dto;

/**
 * Kafka로부터 수신한 '결제 완료' 메시지를 담는 데이터 모델
 */
data class PaymentResultMessage(
     val orderId: Long,
     val success: Boolean,
     val paymentId: String?,
     val reason: String?
)
