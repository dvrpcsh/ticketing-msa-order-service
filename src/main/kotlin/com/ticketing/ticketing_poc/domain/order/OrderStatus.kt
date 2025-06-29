package com.ticketing.ticketing_poc.domain.order

/**
 * 주문의 상태를 나타내는 Enum클래스
 */
enum class OrderStatus {
    PENDING_FOR_PAYMENT,    //결제 대기중
    RESERVED,               //예약 완료
    CANCELED                //예약 취소
}