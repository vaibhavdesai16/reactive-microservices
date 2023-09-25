package com.practice.crs.service

import com.practice.crs.exception.InvalidDateRange
import com.practice.crs.repository.ConsumptionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class ConsumptionService( val consumptionRepository: ConsumptionRepository) {

    fun calculateConsumption(customerId : Long, startDateTime: LocalDateTime, endDateTime: LocalDateTime) : Mono<Float>? {
        validateDate(startDateTime, endDateTime)
        val initialConsumption =  consumptionRepository.findLatestByCustomerIdAndTimestamp(customerId, startDateTime)
        val finalConsumption =  consumptionRepository.findLatestByCustomerIdAndTimestamp(customerId, endDateTime)
        return initialConsumption.zipWith(finalConsumption) { c1, c2 -> c2.kw - c1.kw }
    }

    fun validateDate(startDateTime: LocalDateTime, endDateTime: LocalDateTime){
        if(startDateTime > endDateTime) throw InvalidDateRange()
    }
}