package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.order.dto.CreateOrderRequest
import com.ticketing.ticketing_poc.domain.order.dto.OrderResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    /**
     * 내 주문 내역 목록 조회 API
     * (실제 서비스에선 JWT토큰에서 userId를 추출해야하지만 지금은 파라미터로 받고 추후 구현)
     */
    @Operation(summary = "내 주문 내역 목록 조회")
    @GetMapping
    fun getMyOrders(@RequestHeader("X-User-Id") userId: Long): ResponseEntity<List<OrderResponse>> {
        val orders = orderService.getMyOrders(userId)

        return ResponseEntity.ok(orders)
    }

    /**
     * 주문 취소 API
     * (실제 서비스에선 JWT토큰에서 userId추출 ... 추후 구현)
     */
    @Operation(summary = "주문 취소")
    @DeleteMapping("/{orderId}")
    fun cancelOrder(
        @PathVariable orderId: Long,
        @RequestHeader("X-User-Id") userId: Long
    ): ResponseEntity<String> {
        orderService.cancelOrder(orderId, userId)

        return ResponseEntity.ok("주문(ID: $orderId)이 성공적으로 취소되었습니다.")
    }
}