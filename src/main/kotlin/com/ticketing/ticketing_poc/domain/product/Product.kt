package com.ticketing.ticketing_poc.domain.product

import com.ticketing.ticketing_poc.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * 판매 대상이 되는 상품(이벤트) 정보를 담는 Entity
 */
@Entity
@Table(name = "product")
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    //상품(이벤트)의 제목
    @Column(nullable = false)
    var title: String,

    //상품(이벤트)의 상세 설명
    @Column(nullable = false, length = 1000)
    var description: String,

    //상품 카테고리(야구, 콘서트 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: ProductCategory,

    //상영 또는 경기 시간(분 단위)
    @Column(nullable = false)
    var runningTime: Int,

    //관람 등급
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var ageRating: AgeRating,

    //이벤트 시작 시간
    @Column(nullable = false)
    var startTime: LocalDateTime,

    //이벤트 종료 시간
    @Column(nullable = false)
    var endTime: LocalDateTime,

    //공연 또는 경기가 열리는 장소 이름
    var venueName: String

): BaseEntity() // 생성/수정 시간을 자동으로 관리하기 위해 BaseEntity상속
