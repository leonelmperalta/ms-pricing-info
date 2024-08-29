package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.application.service.PriceQueryService;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryDTO;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryResponseDTO;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.mapper.PriceQueryMapper;
import com.lperalta.ms.pricing.info.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
class PriceControllerTest {

    @MockBean
    private PriceQueryService priceQueryService;

    @MockBean
    private PriceQueryMapper priceQueryMapper;

    @Autowired
    private PriceController priceController;

    @Test
    public void givenValidRequest_whenPriceQuery_thenReturnPriceResponse()
            throws Exception {
        Long brandId = 1L;
        Long productId = 35455L;
        String applicationDate = "2020-06-15 18:00:00";
        PriceQuery priceQuery = TestUtils.singlePriceQuery().get(0);

        Mockito.when(this.priceQueryService.priceQuery(eq(brandId), eq(productId), eq(applicationDate)))
                .thenReturn(priceQuery);

        Mockito.when(this.priceQueryMapper.toDto(priceQuery))
                .thenReturn(PriceQueryMapper.INSTANCE.toDto(priceQuery));

        ResponseEntity<PriceQueryDTO> response = this.priceController.priceQuery(applicationDate, productId, brandId);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(1L, Objects.requireNonNull(response.getBody()).getBrandId());
        assertEquals(4L, response.getBody().getFeeId());
    }

}