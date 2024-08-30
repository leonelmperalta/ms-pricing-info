package com.lperalta.ms.pricing.info.prices.domain.service;

import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PricePriorityServiceTest {

    private PricePriorityService pricePriorityService;

    @BeforeEach
    void setUp() {
        pricePriorityService = new PricePriorityService();
    }

    @Test
    void testGetHighestPriorityPrice_SingleHighestPriority() throws PriceConfigurationErrorException, NotDataFoundException {
        PriceQuery price1 = PriceQuery.builder().priority(1).build();
        PriceQuery price2 = PriceQuery.builder().priority(2).build();
        PriceQuery price3 = PriceQuery.builder().priority(3).build();
        List<PriceQuery> prices = Arrays.asList(price1, price2, price3);

        PriceQuery result = pricePriorityService.getHighestPriorityPrice(prices);

        assertEquals(price3, result);
    }

    @Test
    void testGetHighestPriorityPrice_MultipleHighestPriority_ThrowsPriceConfigurationErrorException() {
        PriceQuery price1 = PriceQuery.builder().priority(3).build();
        PriceQuery price2 = PriceQuery.builder().priority(2).build();
        PriceQuery price3 = PriceQuery.builder().priority(3).build();
        List<PriceQuery> prices = Arrays.asList(price1, price2, price3);

        assertThrows(PriceConfigurationErrorException.class, () -> {
            pricePriorityService.getHighestPriorityPrice(prices);
        });
    }

    @Test
    void testGetHighestPriorityPrice_EmptyList_ThrowsNotDataFoundException() {
        List<PriceQuery> prices = List.of();

        assertThrows(NotDataFoundException.class, () -> {
            pricePriorityService.getHighestPriorityPrice(prices);
        });
    }
}