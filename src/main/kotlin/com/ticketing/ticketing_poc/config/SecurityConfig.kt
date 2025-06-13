package com.ticketing.ticketing_poc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

/**
 * Spring Security 관련 설정을 정의하는 클래스
 * -비밀번호 암호화에 사용할 PasswordEencoder Bean을 등록
 * -웹 페이지 및 API에 대한 접근 제어 규칙(인가 설정)을 구성
 */
@Configuration
class SecurityConfig {

    //비밀번호 암호화를 위한 PasswordEncoder를 Bean으로 등록
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    //인증/인가(Authentication/Authorization)관련 설정을 위한 SecurityFilterChain을 Bean으로 등록
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        //공개적으로 접근을 허용할 URL 목록을 정의합니다.
        val publicUrls = arrayOf(
            "/api/users/signup",        //회원가입 API
            "/swagger-ui/**",           //Swagger UI페이지
            "/v3/api-docs/**",          //Swagger API문서
            "/api/products"             //상품 목록 조회 API는 모두에게 허용
        )

        /**
         * 1.CSRF(Cross-Site Request Forgery)보호 기능을 비활성화합니다.(REST API에서는 세션을 사용하지 않으므로 불필요)
         * 2.HTTP요청에 대한 인가(Authorization)규칙을 설정합니다.
         * 3.publicUrls에 포함된 경로는 인증 없이 누구나 접근할 수 있도록 허용합니다.
         * 4.그 이외 모든 요청은 반드시 인증을 거쳐야 접근할 수 있도록 설정합니다.
         */
        http
            .csrf {it.disable()}
            .httpBasic {it.disable()}
            .formLogin{it.disable()}
            .authorizeHttpRequests{
                it.requestMatchers(*publicUrls).permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }
}