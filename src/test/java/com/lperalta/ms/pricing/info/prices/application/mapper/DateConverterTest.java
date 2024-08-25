package com.lperalta.ms.pricing.info.prices.application.mapper;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DateConverterTest {

    @Autowired
    private DateConverter dateConverter;

    @Test
    public void givenValidDate_whenToLocalDateTime_thenReturnValidDateTime() throws InternalServerErrorException {
        String date = "2020-06-14 16:00:00";
        LocalDateTime result = this.dateConverter.toLocalDateTime(date);

        assertEquals(2020, result.getYear());
        assertEquals(6, result.getMonthValue());
        assertEquals(14, result.getDayOfMonth());
        assertEquals(16, result.getHour());
        assertEquals(0, result.getMinute());
        assertEquals(0, result.getSecond());
    }

    @Test
    public void givenInvalidDate_whenToLocalDateTime_thenThrowInternalServerError() {
        String date = "2020-06-14T16:00:00Zasdfadsf";
        assertThrows(InternalServerErrorException.class, () -> this.dateConverter.toLocalDateTime(date));
    }

    @Test
    public void givenValidLocalDateTime_whenToIso8604String_thenReturnValidStringDate() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 16, 0 ,0);
        String result = this.dateConverter.toISO8601String(localDateTime);
        assertEquals("2020-06-14 16:00:00", result);
    }

}