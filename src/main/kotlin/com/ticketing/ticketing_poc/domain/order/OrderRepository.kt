package com.ticketing.ticketing_poc.domain.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Order Entity에 대한 데이터베이스 접근을 처리하는 레포지토리입니다.
 */
@Repository
interface OrderRepository : JpaRepository<Order, Long>