package com.ticketing.ticketing_poc.domain.order

import com.ticketing.ticketing_poc.domain.common.BaseEntity
import jakarta.persistence.*

/**
 * 역할: 사용자의 상품 예매 정보를 나타내는 Entity
 * 흐름:
 * 1. 다른 마이크로서비스인 User와의 직접적인 @ManyToOne 관계를 제거합니다.
 * 2. 대신, 어떤 사용자의 주문인지를 식별하기 위해 사용자의 ID(userId)만 Long 타입으로 저장합니다.
 */
@Table(name = "orders")
@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus,

    // User Entity 참조를 userId(Long)으로 변경
    @Column(name = "user_id", nullable = false)
    val userId: Long,

    // Product Entity 참조를 productId(Long)으로 변경
    @JoinColumn(name = "product_id", nullable = false)
    val productId: Long,

    @Column(nullable = false)
    val seatId: String

) : BaseEntity() // 새로 만든 BaseEntity 상속
