package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Order관련 비즈니스 로직을 처리하는 서비스
 */
@Service
class OrderService (
    private val orderRepository: OrderRepository
) {
    /**
     * 신규 주문 생성
     *
     * 1.요청으로 들어온 userId와 productId 값을 그대로 신뢰하고 사용합니다.
     * 2.위 ID들을 사용하여 새로운 Order Entity를 생성합니다.
     * 3.생성된 Order를 DB에 저장하고, 저장된 정보를 DTO로 변환하여 반환합니다.
     */
    @Transactional
    fun createOrder(request: CreateOrderRequest): OrderResponse {

        //1.요청으로 들어온 userId 값과 조회된 ProductID를 바탕으로 Order Entity를 생성합니다.
        val order = Order(
            userId = request.userId, //User객체 대신 userId를 직접 사용
            productId = request.productId,
            status = OrderStatus.RESERVED
        )

        //3.생성된 Order를 DB에 저장합니다.
        val savedOrder = orderRepository.save(order)

        //4.저장된 Order 정보를 바탕으로 OrderResponseDto를 생성하여 반환합니다.
        return OrderResponse.from(savedOrder)

    }
}