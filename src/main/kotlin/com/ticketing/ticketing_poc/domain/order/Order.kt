package com.ticketing.ticketing_poc.domain.order

import com.fasterxml.jackson.databind.ser.Serializers.Base
import com.ticketing.ticketing_poc.domain.common.BaseEntity
import com.ticketing.ticketing_poc.domain.product.Product
import com.ticketing.ticketing_poc.domain.user.User
import jakarta.persistence.*

/**
 * 사용자의 상품 예매 정보를 나타내는 Entity
 * User, Product와 다대일(N:1) 관계를 맺습니다.
 */
//'order'는 SQL에서 정렬을 의미하는 예약어(ORDER BY)이므로, 테이블 이름을 'orders'로 지정해야 안전합니다.
@Table(name = "orders")
@Entity
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    //주문 상태(예약완료, 취소 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus,

    //이 주문이 어떤 사용자에 의해 생성되었는지를 나타냄
    //Order(N) : User(1) 관계
    @ManyToOne(fetch = FetchType.LAZY)
    //데이터베이스의 외래 키 컬럼을 'user_id'로 지정합니다.
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    //이 주문이 어떤 상품에 대한 것인지를 나타냄
    //Order(N) : Product(1) 관계
    @ManyToOne(fetch = FetchType.LAZY)
    //데이터베이스의 외래 키 컬럼을 'product_id'로 지정합니다.
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
) : BaseEntity()