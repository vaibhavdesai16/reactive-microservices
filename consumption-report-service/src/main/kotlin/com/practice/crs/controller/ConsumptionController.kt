package com.practice.crs.controller

import com.practice.crs.service.ConsumptionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@RestController
@RequestMapping("/consumption")
class ConsumptionController(
    private val consumptionService: ConsumptionService
) {

    @GetMapping
    fun getConsumptionForUser(@RequestParam("userId") userId: Long,
                              @RequestParam("startDate") startDate: String,
                              @RequestParam("endDate") endDate: String): Mono<Float>? {
        return consumptionService.calculateConsumption(userId, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }
}