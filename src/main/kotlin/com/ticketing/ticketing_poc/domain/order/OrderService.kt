package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import com.ticketing.ticketing_poc.kafka.OrderKafkaProducer
import jakarta.persistence.EntityNotFoundException
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
            seatId = request.seatId,
            status = OrderStatus.PENDING_FOR_PAYMENT
        )

        //1-1.생성된 Order를 DB에 저장합니다.
        val savedOrder = orderRepository.save(order)

        //2.Kafka 메시지 발행
        kafkaProducer.sendOrderCreationMessage(
            orderId =  savedOrder.id!!,
            userId =  savedOrder.userId,
            productId = savedOrder.productId,
            seatId = savedOrder.seatId
        )

        //4.저장된 Order 정보를 바탕으로 OrderResponseDto를 생성하여 반환합니다.
        return OrderResponse.from(savedOrder)

    }

    /**
     * 결제가 완료된 주문의 상태를 'RESERVED'(예약 확정)로 변경합니다.
     *
     * 1.Kafka로부터 전달받은 orderId로 주문을 조회합니다. 없으면 예외 발생
     * 2.조회된 주문의 상태를 RESERVED로 변경합니다.(JPA의 더티 체킹(Dirty Cheking)에 의해 트랜잭션이 끝나면 자동으로 DB에 업데이트)
     */
    @Transactional
    fun updateOrderStatusToReserved(orderId: Long) {
        val order = orderRepository.findById(orderId)
            .orElseThrow() { EntityNotFoundException("주문(ID: $orderId)을 찾을 수 없습니다.")}

        order.status = OrderStatus.RESERVED
    }

}