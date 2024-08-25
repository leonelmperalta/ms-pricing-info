package com.lperalta.ms.pricing.info.prices.infraestructure.in.mapper;

import com.lperalta.ms.pricing.info.prices.application.mapper.DateConverter;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceQueryMapper {

    private final DateConverter dateConverter;

    @Autowired
    public PriceQueryMapper(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }


    public PriceQueryResponseDTO toPriceQueryResponse(PriceQuery priceQuery) {
        return PriceQueryResponseDTO.builder()
                .applicationEndDate(this.dateConverter.toISO8601String(priceQuery.getApplicationEndDate()))
                .applicationStartDate(this.dateConverter.toISO8601String(priceQuery.getApplicationStartDate()))
                .brandId(priceQuery.getBrandId())
                .currency(priceQuery.getCurrency())
                .feeId(priceQuery.getFeeId())
                .finalPrice(priceQuery.getFinalPrice())
                .productId(priceQuery.getProductId())
                .build();
    }
}
