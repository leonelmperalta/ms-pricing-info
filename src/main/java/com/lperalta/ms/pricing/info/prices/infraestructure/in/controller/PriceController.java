package com.lperalta.ms.pricing.info.prices.infraestructure.in.controller;

import com.lperalta.ms.pricing.info.prices.domain.port.in.PriceQueryUseCase;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.api.PriceInfoControllerV1Api;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryDTO;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.mapper.PriceQueryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController implements PriceInfoControllerV1Api {

    private final PriceQueryUseCase priceQueryUseCase;
    private final PriceQueryMapper priceQueryMapper;

    public PriceController(PriceQueryUseCase priceQueryUseCase,
                           PriceQueryMapper priceQueryMapper) {
        this.priceQueryUseCase = priceQueryUseCase;
        this.priceQueryMapper = priceQueryMapper;
    }

    @Override
    public ResponseEntity<PriceQueryDTO> priceQuery(String applicationDate, Long productId, Long brandId)
            throws Exception {
        PriceQuery priceQuery = this.priceQueryUseCase.priceQuery(brandId, productId, applicationDate);
        return ResponseEntity.ok(this.priceQueryMapper.toDto(priceQuery));
    }
}