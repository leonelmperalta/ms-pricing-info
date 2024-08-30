package com.lperalta.ms.pricing.info.prices.application.service.impl;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.application.mapper.DateConverter;
import com.lperalta.ms.pricing.info.prices.application.service.PriceQueryService;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.domain.port.out.PriceQueryRepository;
import com.lperalta.ms.pricing.info.prices.domain.service.PricePriorityService;
import com.lperalta.ms.pricing.info.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest(classes = {PriceQueryService.class})
class PriceQueryServiceTest {

    @MockBean
    private PricePriorityService pricePriorityService;

    @MockBean
    private PriceQueryRepository priceQueryRepository;

    @MockBean
    private DateConverter dateConverter;

    @Autowired
    @InjectMocks
    private PriceQueryService priceQueryService;

    @Test
    public void givenValidRequest_whenPriceQuery_thenReturnFirstMatchingPrice()
            throws InternalServerErrorException, NotDataFoundException, PriceConfigurationErrorException {
        Long brandId = 1L;
        Long productId = 35455L;
        String applicationDate = "2020-06-15 18:00:00";
        LocalDateTime applicationDateAsDate = LocalDateTime.of(
                2020, 6, 15, 18, 0, 0
        );
        List<PriceQuery> priceQueries = TestUtils.singlePriceQuery();

        Mockito.when(this.dateConverter.toLocalDateTime(eq(applicationDate))).thenReturn(applicationDateAsDate);

        Mockito.when(this.priceQueryRepository.findPrices(
                eq(productId), eq(brandId), eq(applicationDateAsDate)
        )).thenReturn(priceQueries);

        PriceQuery result = this.priceQueryService.priceQuery(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(priceQueries.get(0), result);
    }

    @Test
    public void givenValidRequest_whenPriceQuery_thenReturnHighestPriorityPrice()
            throws InternalServerErrorException, NotDataFoundException, PriceConfigurationErrorException {
        Long brandId = 1L;
        Long productId = 35455L;
        String applicationDate = "2020-06-14 16:00:00";
        LocalDateTime applicationDateAsDate = LocalDateTime.of(
                2020, 6, 14, 16, 0, 0
        );
        List<PriceQuery> priceQueries = TestUtils.multiplePriceQuery();
        PriceQuery highestPriorityPrice = priceQueries.get(1);

        Mockito.when(this.dateConverter.toLocalDateTime(eq(applicationDate))).thenReturn(applicationDateAsDate);

        Mockito.when(this.priceQueryRepository.findPrices(
                eq(productId), eq(brandId), eq(applicationDateAsDate)
        )).thenReturn(priceQueries);

        Mockito.when(this.pricePriorityService.getHighestPriorityPrice(any())).thenReturn(highestPriorityPrice);

        PriceQuery result = this.priceQueryService.priceQuery(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(2, result.getFeeId());
        assertEquals(BigDecimal.valueOf(25.45), result.getFinalPrice());
    }

    @Test
    public void givenInvalidArguments_whenPriceQuery_thenReturnNotDataFound()
            throws InternalServerErrorException {
        Long brandId = 1L;
        Long productId = 35455L;
        String applicationDate = "2020-06-14 16:00:00";
        LocalDateTime applicationDateAsDate = LocalDateTime.of(
                2020, 6, 14, 16, 0, 0
        );
        List<PriceQuery> priceQueries = new ArrayList<>();

        Mockito.when(this.dateConverter.toLocalDateTime(eq(applicationDate))).thenReturn(applicationDateAsDate);

        Mockito.when(this.priceQueryRepository.findPrices(
                eq(productId), eq(brandId), eq(applicationDateAsDate)
        )).thenReturn(priceQueries);

        assertThrows(NotDataFoundException.class, () -> this.priceQueryService.priceQuery(brandId, productId, applicationDate));
    }
}