package com.lperalta.ms.pricing.info.prices.application.mapper;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateConverter {

    public static final String ISO_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public LocalDateTime toLocalDateTime(String date) throws InternalServerErrorException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_DATE_TIME);
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InternalServerErrorException();
        }
    }

    public String toISO8601String(LocalDateTime localDateTime) {
        return localDateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(ISO_DATE_TIME));
    }
}
