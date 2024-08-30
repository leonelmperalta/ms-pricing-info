package com.lperalta.ms.pricing.info.prices.domain.port.in;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;

public interface PriceQueryUseCase {
    PriceQuery priceQuery(Long brandId, Long productId, String applicationDate)
            throws InternalServerErrorException, NotDataFoundException, PriceConfigurationErrorException;
}
