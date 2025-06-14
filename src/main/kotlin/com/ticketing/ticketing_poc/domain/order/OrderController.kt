package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Order 도메인과 관련된 HTTP요청을 받고 처리하는 API의 진입점
 */
@Tag(name = "주문 API", description = "주문 생성 등 관련 기능을 제공하는 API입니다.")
@RestController
@RequestMapping("/api/orders")
class OrderController (
    private val orderService: OrderService
) {
    /**
     * 신규 주문 생성 API
     */
    @Operation(summary = "신규 주문 생성")
    @PostMapping
    fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<OrderResponse> {
        val order = orderService.createOrder(request)

        return ResponseEntity.status(HttpStatus.CREATED).body(order)
    }

}