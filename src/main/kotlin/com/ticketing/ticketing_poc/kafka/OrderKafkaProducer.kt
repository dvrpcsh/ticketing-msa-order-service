package com.ticketing.ticketing_poc.kafka

import com.ticketing.ticketing_poc.domain.order.dto.OrderCreationMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

/**
 * 주문 관련 메시지를 Kafka로 발행(Produce)하는 클래스
 */
@Component
class OrderKafkaProducer (
    //Spring for Kafka가 자동으로 설정해주는 KafkaTemplate을 주입받습니다.
    //메시지의 키는 String, 값은 OrderCreationMessage 객체로 설정합니다.
    private val kafkaTemplate: KafkaTemplate<String, OrderCreationMessage>
) {
    //메시지를 보낼 토픽(주제)의 이름을 상수로 정의합니다.
    private val TOPIC_NAME = "order-created-v2"

    /**
     * '주문 생성' 메시지를 Kafka로 전송합니다.
     *
     * 흐름
     * 1.전송할 메시지 객체(OrderCreationMessage)를 생성합니다.
     * 2.kafkaTemplate.send()메서드를 사용하여 지정된 토픽으로 메시지를 보냅니다.
     */
    fun sendOrderCreationMessage(orderId: Long, userId: Long, productId: Long, seatId: String) {
        val message = OrderCreationMessage(
            orderId = orderId,
            userId = userId,
            productId = productId,
            seatId = seatId
        )
        kafkaTemplate.send(TOPIC_NAME, message)
    }
}