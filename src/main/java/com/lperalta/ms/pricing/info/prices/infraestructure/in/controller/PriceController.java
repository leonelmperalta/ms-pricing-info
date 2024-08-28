package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.application.service.PriceQueryService;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.api.PriceControllerV1Api;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryDTO;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.mapper.PriceQueryMapper;
import org.springframework.http.ResponseEntity;


public class PriceController implements PriceControllerV1Api {

    private final PriceQueryService priceQueryService;
    private final PriceQueryMapper priceQueryMapper;

    public PriceController(PriceQueryService priceQueryService,
                           PriceQueryMapper priceQueryMapper) {
        this.priceQueryService = priceQueryService;
        this.priceQueryMapper = priceQueryMapper;
    }

    @Override
    public ResponseEntity<PriceQueryDTO> priceQuery(String applicationDate, Long productId, Long brandId)
            throws PriceConfigurationErrorException, InternalServerErrorException, NotDataFoundException {
        return null;
    }
}