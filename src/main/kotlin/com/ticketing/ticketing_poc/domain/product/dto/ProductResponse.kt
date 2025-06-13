package com.ticketing.ticketing_poc.domain.product.dto

import com.ticketing.ticketing_poc.domain.product.Product
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 상품 정보를 클라이언트에게 응답할 때 사용하는 DTO
 * Entity를 직접 노출하지 않고, 필요한 정보만 가공해서 전달하기 위함
 */
@Schema(description = "상품 정보 응답 데이터 모델")
data class ProductResponse (
    val id: Long,
    val title: String,
    val startTime: LocalDateTime,
    val venueName: String
) {
    companion object {
        //Product Entity를 ProductResponse DTO로 변환하는 정적 팩토리 메서드
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!, //id는 항상 존재한다고 가정
                title = product.title,
                startTime = product.startTime,
                venueName = product.venueName
            )
        }
    }
}