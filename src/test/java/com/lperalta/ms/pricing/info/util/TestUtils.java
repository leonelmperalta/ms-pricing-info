package com.lperalta.ms.pricing.info.util;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtils {
    public static List<PriceQuery> singlePriceQuery() {
        LocalDateTime startDate = LocalDateTime.of(
                2020, 6, 15, 16, 0 ,0
        );

        LocalDateTime endDate = LocalDateTime.of(
                2020, 12, 31, 23, 59 ,59
        );

        PriceQuery price = PriceQuery.builder()
                .applicationEndDate(endDate)
                .applicationStartDate(startDate)
                .brandId(1L)
                .currency("EUR")
                .feeId(4L)
                .finalPrice(BigDecimal.valueOf(38.95))
                .priority(1)
                .productId(35455L)
                .build();

        return Collections.singletonList(price);
    }

    public static List<PriceQuery> multiplePriceQuery() {
        List<PriceQuery> priceQueries = new ArrayList<>();

        LocalDateTime startDateFee1 = LocalDateTime.of(
                2020, 6, 14, 0, 0 ,0
        );

        LocalDateTime endDateFee1 = LocalDateTime.of(
                2020, 12, 31, 23, 59 ,59
        );

        PriceQuery priceFee1 = PriceQuery.builder()
                .applicationEndDate(endDateFee1)
                .applicationStartDate(startDateFee1)
                .brandId(1L)
                .currency("EUR")
                .feeId(1L)
                .finalPrice(BigDecimal.valueOf(35.50))
                .priority(0)
                .productId(35455L)
                .build();

        LocalDateTime startDateFee2 = LocalDateTime.of(
                2020, 6, 14, 15, 0 ,0
        );

        LocalDateTime endDateFee2 = LocalDateTime.of(
                2022, 6, 14, 18, 30 ,0
        );

        PriceQuery priceFee2 = PriceQuery.builder()
                .applicationEndDate(endDateFee2)
                .applicationStartDate(startDateFee2)
                .brandId(1L)
                .currency("EUR")
                .feeId(2L)
                .finalPrice(BigDecimal.valueOf(25.45))
                .priority(1)
                .productId(35455L)
                .build();

        priceQueries.add(priceFee1);
        priceQueries.add(priceFee2);

        return priceQueries;
    }

    public static List<PriceQuery> multiplePriceQueryBothApplicable() {
        List<PriceQuery> priceQueries = new ArrayList<>();

        LocalDateTime startDateFee1 = LocalDateTime.of(
                2020, 6, 14, 0, 0 ,0
        );

        LocalDateTime endDateFee1 = LocalDateTime.of(
                2020, 12, 31, 23, 59 ,59
        );

        PriceQuery priceFee1 = PriceQuery.builder()
                .applicationEndDate(endDateFee1)
                .applicationStartDate(startDateFee1)
                .brandId(1L)
                .currency("EUR")
                .feeId(1L)
                .finalPrice(BigDecimal.valueOf(35.50))
                .priority(1)
                .productId(35455L)
                .build();

        LocalDateTime startDateFee2 = LocalDateTime.of(
                2020, 6, 14, 15, 0 ,0
        );

        LocalDateTime endDateFee2 = LocalDateTime.of(
                2022, 6, 14, 18, 30 ,0
        );

        PriceQuery priceFee2 = PriceQuery.builder()
                .applicationEndDate(endDateFee2)
                .applicationStartDate(startDateFee2)
                .brandId(1L)
                .currency("EUR")
                .feeId(2L)
                .finalPrice(BigDecimal.valueOf(25.45))
                .priority(1)
                .productId(35455L)
                .build();

        priceQueries.add(priceFee1);
        priceQueries.add(priceFee2);

        return priceQueries;
    }

    public static PriceQueryResponseDTO getPriceQueryResponse() {
        return PriceQueryResponseDTO.builder()
                .applicationEndDate("2020-12-31T23:59:59")
                .applicationStartDate("2020-6-15T16:00:00")
                .brandId(1L)
                .currency("EUR")
                .feeId(4L)
                .finalPrice(BigDecimal.valueOf(38.95))
                .productId(35455L)
                .build();
    }
}
