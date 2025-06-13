package com.ticketing.ticketing_poc.domain.product

import com.ticketing.ticketing_poc.domain.product.dto.CreateProductRequest
import com.ticketing.ticketing_poc.domain.product.dto.ProductResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Product관련 비즈니스 로직을 처리하는 서비스
 */
@Service
class ProductService (
    private val productRepository: ProductRepository
) {
    /**
     * 신규 상품 생성
     *
     * 1.요청 DTO를 기반으로 Product Entity를 생성합니다.
     * 2.생성된 Entity를 데이터베이스에 저장합니다.
     * 3.저장된 Entity를 Response DTO로 변환하여 반환합니다.
     */
    @Transactional
    fun createProduct(request: CreateProductRequest): ProductResponse {
        val product = Product(
            title = request.title,
            description = request.description,
            category = request.category,
            runningTime = request.runningtime,
            ageRating = request.ageRating,
            startTime = request.startTime,
            endTime = request.endTime,
            venueName = request.venueName
        )

        val savedProduct = productRepository.save(product)

        return ProductResponse.from(savedProduct)
    }

    /**
     * 모든 상품 목록 조회
     *
     * 1.데이터베이스에서 모든 Product를 조회합니다.
     * 2.조회된 Product Entity 목록을 ProductResponse DTO 목록으로 반환합니다.
     */
    @Transactional(readOnly = true) //읽기 전용 트랜잭션으로 성능 최적화
    fun getAllProducts(): List<ProductResponse> {
        return productRepository.findAll().map { ProductResponse.from(it) }
    }
}