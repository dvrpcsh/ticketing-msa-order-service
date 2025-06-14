package com.ticketing.ticketing_poc.domain.order.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 주문 생성을 요청할 때 사용하는 DTO
 */
@Schema(description = "주문 생성을 위한 요청 데이터 모델")
data class CreateOrderRequest(
    @Schema(description = "주문을 요청하는 사용자의 ID", example = "1")
    val userId: Long,

    @Schema(description = "주문할 상품의 ID", example = "1")
    val productId: Long
)
