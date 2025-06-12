package com.ticketing.ticketing_poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class TicketingPocApplication

fun main(args: Array<String>) {
	runApplication<TicketingPocApplication>(*args)
}
