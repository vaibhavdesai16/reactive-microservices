package com.practice.crs.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "consumption")
data class Consumption(
    @Id
    val id: Long? = null,
    val customerId: Long,
    val timestamp: LocalDateTime,
    val kw: Float
)







