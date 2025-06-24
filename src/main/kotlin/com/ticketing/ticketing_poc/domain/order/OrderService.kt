package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import com.ticketing.ticketing_poc.kafka.OrderKafkaProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Order관련 비즈니스 로직을 처리하는 서비스
 */
@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val kafkaProducer: OrderKafkaProducer
) {
    /**
     * 신규 주문 생성
     *
     * 1.요청으로 들어온 userId와 productId 값을 바탕으로 Order Entity 생성
     * 2.저장된 주문 정보를 바탕으로 '주문 생성됨' 메시지를 Kafka로 전송
     * 3.생성된 Order를 DB에 저장하고, 저장된 정보를 DTO로 변환하여 반환
     */
    @Transactional
    fun createOrder(request: CreateOrderRequest): OrderResponse {

        //1.요청으로 들어온 userId 값과 조회된 ProductID를 바탕으로 Order Entity를 생성합니다.
        val order = Order(
            userId = request.userId,
            productId = request.productId,
            status = OrderStatus.RESERVED
        )

        //1-1.생성된 Order를 DB에 저장합니다.
        val savedOrder = orderRepository.save(order)

        //2.Kafka 메시지 발행
        kafkaProducer.sendOrderCreationMessage(
            orderId =  savedOrder.id!!,
            userId =  savedOrder.userId,
            productId = savedOrder.productId
        )

        //4.저장된 Order 정보를 바탕으로 OrderResponseDto를 생성하여 반환합니다.
        return OrderResponse.from(savedOrder)

    }
}