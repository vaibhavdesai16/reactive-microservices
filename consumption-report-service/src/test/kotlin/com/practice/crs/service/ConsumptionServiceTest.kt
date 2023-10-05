package com.practice.crs.service

import com.practice.crs.exception.InvalidDateRange
import io.kotlintest.shouldNotThrow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class ConsumptionServiceTest(
    @Autowired var consumptionService: ConsumptionService) {

    @Test
    fun `should not throw exception if startDate is less than endDate`(){
        // arrange
        val startDate =  LocalDateTime.parse("2023-08-01T19:34:50.63")
        val endDate = LocalDateTime.parse("2023-08-08T19:34:50.63")

        // act and asset
        shouldNotThrow<InvalidDateRange> { consumptionService.validateDate(startDate, endDate)}

    }
}
