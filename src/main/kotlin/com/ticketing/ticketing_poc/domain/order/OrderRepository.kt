package com.ticketing.ticketing_poc.domain.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Order Entity에 대한 데이터베이스 접근을 처리하는 레포지토리입니다.
 */
@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    //특정 사용자의 주문 목록을 ID 역순(최신순)으로 조회하는 쿼리 메서드
    fun findByUserIdOrderByIdDesc(userId: Long): List<Order>
}