 
# ⚾️ 실시간 티켓 예매 플랫폼 (MSA) 프로젝트

MSA(마이크로서비스 아키텍처) 기반의 유연하고 확장 가능한 실시간 티켓 예매 플랫폼을 구축합니다.  
1차 목표는 KBO 리그 야구장 티켓 예매 서비스를 안정적으로 런칭하는 것이며,  
최종적으로는 공연, 미술관 등 모든 종류의 이벤트를 다룰 수 있는 **범용 예약 플랫폼**을 지향합니다.

---

## 🚀 주요 기능 (Core Features)

### ✅ 사용자 기능
- **회원 관리**: 회원가입, 로그인/로그아웃 (JWT 기반), 소셜 로그인
- **이벤트 조회**: 날짜별/종류별 이벤트 목록 조회 및 검색
- **실시간 좌석 선택**: 좌석 배치도 및 실시간 잔여석 확인, 좌석 선점(Lock) 기능
- **결제**: 외부 PG 연동을 통한 결제 처리
- **예매 내역**: 마이페이지에서 예매 내역 확인 및 취소
~~~~
### ✅ 비기능적 목표
- **동시성 제어**: 티켓 오픈 시 10,000명 이상의 동시 접속 처리
- **성능**: 좌석 조회 응답 시간 500ms 이내, 결제 처리 3초 이내
- **가용성 및 확장성**: 개별 서비스의 독립적인 장애 격리 및 확장(Scale-out) 구조

---

## 🏛️ 시스템 아키텍처 (System Architecture)

본 프로젝트는 각 서비스가 독립적으로 역할을 수행하는 마이크로서비스 아키텍처를 따릅니다.  
서비스 간 통신은 API 호출 또는 **비동기 메시지 큐(Kafka)** 를 통해 이루어집니다.

### 🔧 아키텍처 다이어그램

```plaintext
[ 웹/앱 클라이언트 ]
         ↑↓
 [ API 게이트웨이 ]  ← 인증/인가, 라우팅, 로드 밸런싱
         ↑↓
 ┌────────────────────────────────────────────────────────────────────┐
 │ 사용자  │ 상품       │ 좌석 관리 │ 주문    │ 결제   │ 알림              │
 │ 서비스  │ 서비스     │ 서비스    │ 서비스  │ 서비스  │서비스             │
 │  ↑↓    │  ↑↓       │   ↑↓     │  ↑↓    │  ↑↓    │   ↑↓             │
 │ UserDB │ ProductDB │ SeatDB   │ OrderDB│ PayDB  │ NotificationDB   │
 └────────────────────────────────────────────────────────────────────┘
         ↑↓
   [ 메시지 큐 (Kafka) ] ← 서비스 간 비동기 통신
```

### 🧩 마이크로서비스 역할

- **사용자 서비스 (User Service)**: 회원 정보 및 인증(JWT) 관리
- **상품 서비스 (Product Service)**: 야구 경기, 공연 등 판매 상품 정보 관리
- **좌석 관리 서비스 (Seat Mgt Service)**: 실시간 좌석 상태, 선점/해제 관리
- **주문 서비스 (Order Service)**: 주문 생성 및 생명주기 관리
- **결제 서비스 (Payment Service)**: 외부 PG 연동 및 결제 처리
- **알림 서비스 (Notification Service)**: 이메일, SMS 등의 비동기 알림 발송
- **API 게이트웨이 (API Gateway)**: 요청의 단일 진입점, 인증/라우팅/로깅 처리

---

## 💻 기술 스택 (Technology Stack)

### Backend
- **Language**: Kotlin 1.9+
- **Framework**: Spring Boot 3.x
- **ORM & Query**: Spring Data JPA, QueryDSL
- **Security**: Spring Security

### Database
- **RDBMS**: PostgreSQL
- **In-Memory DB**: Redis

### Messaging
- **Message Queue**: Apache Kafka

### API Gateway
- **Gateway**: Spring Cloud Gateway

### DevOps
- **Containerization**: Docker
- **Orchestration**: Kubernetes (K8s)

---

## ▶️ 시작하기 (Getting Started)

> 추후 프로젝트 빌드 및 실행 방법이 이곳에 추가될 예정입니다.
