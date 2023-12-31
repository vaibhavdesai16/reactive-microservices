package com.practice.crs.service

import com.practice.crs.entity.Consumption
import com.practice.crs.exception.InvalidDateRange
import com.practice.crs.exception.NoConsumptionRecordFoundException
import com.practice.crs.repository.ConsumptionRepository
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import reactor.test.StepVerifier

@SpringBootTest
class ConsumptionServiceTest(
    @Autowired var consumptionService: ConsumptionService) {

    @Test
    fun `should calculate consumption when consumption on start and end date is given`(){
        // arrange
        val startDate = LocalDateTime.parse("2023-08-01T19:34:50.63")
        val endDate = LocalDateTime.parse("2023-08-08T19:34:50.63")
        val consumptionStart = Consumption(123L, 1001L, startDate, 20.5F);
        val consumptionEnd = Consumption(123L, 1001L, endDate, 24.5F);
        val consumptionRepository = mockk<ConsumptionRepository>()

        every { consumptionRepository.findLatestByCustomerIdAndTimestamp(any(), startDate) }returns Mono
            .just(consumptionStart)
        every { consumptionRepository.findLatestByCustomerIdAndTimestamp(any(), startDate) } returns Mono
            .just(consumptionEnd)

        // act
        val result = consumptionService.calculateConsumption(123L, startDate, endDate);

        // assert
        StepVerifier.create(result?.flux() ?: Mono.empty())
            .expectNext(4.0F)
        consumptionService.calculateConsumption(123L,startDate,
            endDate)
    }

    @Test
    fun `should throw exception when either date has no consumption record`(){
        // arrange
        val startDate =  LocalDateTime.parse("2023-08-01T19:34:50.63")
        val endDate = LocalDateTime.parse("2023-08-08T19:34:50.63")
        val consumptionStart = Consumption(123L, 1001L, startDate, 20.5F);
        val consumptionRepository = mockk<ConsumptionRepository>()

        every { consumptionRepository.findLatestByCustomerIdAndTimestamp(any(), startDate) }returns Mono
            .just(consumptionStart)
        every { consumptionRepository.findLatestByCustomerIdAndTimestamp(any(), startDate) } returns Mono
            .empty()

        // act and assert
        shouldThrow<NoConsumptionRecordFoundException> { consumptionService.calculateConsumption(123L, startDate, endDate) }

    }

    @Test
    fun `should not throw exception if startDate is less than endDate`(){
        // arrange
        val startDate =  LocalDateTime.parse("2023-08-01T19:34:50.63")
        val endDate = LocalDateTime.parse("2023-08-08T19:34:50.63")

        // act and assert
        shouldNotThrow<InvalidDateRange> { consumptionService.validateDate(startDate, endDate)}
    }

    @Test
    fun `should throw exception if startDate is greater than endDate`(){
        // arrange
        val startDate =  LocalDateTime.parse("2023-08-08T19:34:50.63")
        val endDate = LocalDateTime.parse("2023-08-01T19:34:50.63")

        // act and assert
        shouldThrow<InvalidDateRange> { consumptionService.validateDate(startDate, endDate)}
    }
}
