package com.ticketing.ticketing_poc.kafka

import com.ticketing.ticketing_poc.domain.order.OrderService
import com.ticketing.ticketing_poc.domain.order.dto.PaymentResultMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * 결제 시스템으로부터 '결제 완료' 메시지를 수신하여 주문 상태를 업데이트하는 클래스
 */
@Component
class PaymentKafkaConsumer (
    private val orderService: OrderService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * 'payment-completed'토픽을 구독하고 있다가 결제 완료 메시지가 오면 주문 상태를 변경
     */
    @KafkaListener(topics = ["payment-completed"], groupId = "order-group")
    fun handlePaymentCompletion(message: PaymentResultMessage) {
        logger.info("'결제 완료'메시지를 수신했습니다. 주문 상태 업데이트를 시작합니다.")

        if(message.success) {
            //TODO: 주문 서비스에 상태 업데이트 로직 호출
            //orderService.updateOrderStatusToReserved(message.orderId)
            logger.info("주문(ID: ${message.orderId}) 상태를 '예약 확정'으로 변경해야 합니다.")
        } else {
            //TODO: 결제 실패 시 주문 취소 등 예외 처리 로직
            logger.warn("결제 실패 메시지 수신: 주문(ID: ${message.orderId}), 사유:${message.reason}")
        }
    }
}