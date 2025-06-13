package com.ticketing.ticketing_poc.domain.product

import com.ticketing.ticketing_poc.domain.product.dto.CreateProductRequest
import com.ticketing.ticketing_poc.domain.product.dto.ProductResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * 상품 도메인과 관련된 HTTP요청을 받고 처리하는 API의 진입점
 */
@Tag(name = "상품 API", description = "상품 생성, 조회 등 관련 기능을 제공하는 API입니다.")
@RestController
@RequestMapping("/api/products")
class ProductController (
    private val productService: ProductService
) {
    /**
     * 신규 상품 생성 API
     */
    @Operation(summary = "신규 상품 생성")
    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<ProductResponse> {
        val product = productService.createProduct(request)

        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    /**
     * 전체 상품 목록 조회 API
     */
    @Operation(summary = "전체 상품 목록 조회")
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        val products = productService.getAllProducts()

        return ResponseEntity.status(HttpStatus.OK).body(products)
    }


}