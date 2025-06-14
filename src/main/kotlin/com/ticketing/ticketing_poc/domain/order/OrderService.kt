package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import com.ticketing.ticketing_poc.domain.product.ProductRepository
import com.ticketing.ticketing_poc.domain.user.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Order관련 비즈니스 로직을 처리하는 서비스
 */
@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {
    /**
     * 신규 주문 생성
     *
     * 1.요청으로 들어온 userId로 User Entity를 DB에서 조회합니다.(없으면 예외 발생)
     * 2.요청으로 들어온 productId로 Product Entity를 DB에서 조회합니다.(없으면 예외발생)
     * 3.조회된 User와 Product 정보를 바탕으로 Order Entity를 생성합니다.
     * 4.생성된 Order를 DB에 저장합니다.
     * 5.저장된 Order 정보를 바탕으로 OrderResponseDto를 생성하여 반환합니다.
     */
    @Transactional
    fun createOrder(request: CreateOrderRequest): OrderResponse {
        //1.요청으로 들어온 userId로 User Entity를 DB에서 조회합니다.
        val user = userRepository.findById(request.userId)
            .orElseThrow{EntityNotFoundException("해당 ID의 사용자를 찾을 수 없습니다: ${request.userId}")}

        //2.요청으로 들어온 productId로 Product Entity를 DB에서 조회합니다.
        val product = productRepository.findById(request.productId)
            .orElseThrow{EntityNotFoundException("해당 ID의 상품을 찾을 수 없습니다: ${request.productId}")}

        //3.조회된 User와 Product정보를 바탕으로 Order Entity를 생성합니다.
        val order = Order(
            user = user,
            product= product,
            status = OrderStatus.RESERVED
        )

        //4.생성된 Order를 DB에 저장합니다.
        val savedOrder = orderRepository.save(order)

        //5.저장된 Order 정보를 바탕으로 OrderResponseDto를 생성하여 반환합니다.
        return OrderResponse.from(savedOrder)

    }
}