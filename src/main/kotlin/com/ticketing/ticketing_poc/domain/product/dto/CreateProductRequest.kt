package com.ticketing.ticketing_poc.domain.product.dto

import com.ticketing.ticketing_poc.domain.product.AgeRating
import com.ticketing.ticketing_poc.domain.product.ProductCategory
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.cglib.core.Local
import java.time.LocalDateTime

/**
 * 상품 생성을 요청할 때 사용하는 DTO
 */
@Schema(description = "상품 생성을 위한 요청 데이터 모델")
data class CreateProductRequest (
    @Schema(description = "상품 제목", example = "두산 베어스 vs LG 트윈스")
    val title: String,

    @Schema(description = "상품 상세 설명", example = "6월 13일 잠실 야구장에서 열리는 빅매치!")
    val description: String,

    @Schema(description = "상품 카테고리", example = "BASEBALL")
    val category: ProductCategory,

    @Schema(description = "상영/경기 시간(분 단위)", example = "180")
    var runningtime: Int,

    @Schema(description = "관람 등급", example = "ALL")
    val ageRating: AgeRating,

    @Schema(description = "이벤트 시작 시간", example = "2025-06-13T18:30:00")
    val startTime: LocalDateTime,

    @Schema(description = "이벤트 종료 시간", example = "2025-06-13T21:30:00")
    val endTime: LocalDateTime,

    @Schema(description = "장소 이름", example = "잠실 야구장")
    val venueName: String
)