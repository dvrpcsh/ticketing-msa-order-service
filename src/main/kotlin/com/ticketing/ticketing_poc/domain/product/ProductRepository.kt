package com.ticketing.ticketing_poc.domain.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Product Entity에 대한 데이터베이스 접근을 처리하는 레포지토리입니다.
 */
@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    // JpaRepository<T, ID>를 상속받습니다.
    // Product Entity를 다루며 PK Type = Long

}