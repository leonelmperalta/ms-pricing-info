package com.lperalta.ms.pricing.info.prices.application.service;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;

public interface PriceQueryService {
    PriceQuery priceQuery(Long brandId, Long productId, String applicationDate)
            throws InternalServerErrorException, NotDataFoundException, PriceConfigurationErrorException;
}
