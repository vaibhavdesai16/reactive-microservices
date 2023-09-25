package com.practice.crs.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "customer")
data class Customer(
    @Id val customerId: Long?,
    val firstName: String,
    val lastName: String,
    val city: String,
    val consumptions: List<Consumption> = mutableListOf()
)