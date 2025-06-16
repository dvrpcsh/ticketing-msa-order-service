package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import com.ticketing.ticketing_poc.domain.product.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Order관련 비즈니스 로직을 처리하는 서비스
 */
@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) {
    /**
     * 신규 주문 생성
     *
     * 1.요청으로 돌어온 productId로 Product Entity만 DB에서 조회합니다.
     * 2.요청으로 들어온 userId 값과 조회된 Product 정보를 바탕으로 Order Entity를 생성합니다.
     * 3.생성된 Order를 DB에 저장하고, 저장된 정보를 DTO로 변환하여 반환합니다.
     */
    @Transactional
    fun createOrder(request: CreateOrderRequest): OrderResponse {
        //1.요청으로 돌어온 productId로 Product Entity만 DB에서 조회합니다.
        var product = productRepository.findById(request.productId)
            .orElseThrow { EntityNotFoundException("해당 ID의 상품을 찾을 수 없습니다: ${request.productId}") }

        //2.요청으로 들어온 userId 값과 조회된 Product 정보를 바탕으로 Order Entity를 생성합니다.
        val order = Order(
            userId = request.userId, //User객체 대신 userId를 직접 사용
            product = product,
            status = OrderStatus.RESERVED
        )

        //3.생성된 Order를 DB에 저장합니다.
        val savedOrder = orderRepository.save(order)

        //4.저장된 Order 정보를 바탕으로 OrderResponseDto를 생성하여 반환합니다.
        return OrderResponse.from(savedOrder)

    }
}