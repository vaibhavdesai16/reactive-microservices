package com.practice.crs.repository

import com.practice.crs.entity.Consumption
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@Repository
interface ConsumptionRepository: ReactiveCrudRepository<Consumption, Long> {

    @Query("SELECT * FROM consumption WHERE customer_id = :customerId AND timestamp <= :timestamp ORDER BY timestamp DESC LIMIT 1")
    fun findLatestByCustomerIdAndTimestamp(customerId: Long?, timestamp: LocalDateTime?): Mono<Consumption>
}