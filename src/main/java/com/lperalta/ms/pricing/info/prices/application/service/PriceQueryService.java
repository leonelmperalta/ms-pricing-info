package com.lperalta.ms.pricing.info.prices.application.service;

import com.lperalta.ms.pricing.info.prices.application.exception.InternalServerErrorException;
import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.application.mapper.DateConverter;
import com.lperalta.ms.pricing.info.prices.domain.port.in.PriceQueryUseCase;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.domain.port.out.PriceQueryRepository;
import com.lperalta.ms.pricing.info.prices.domain.service.PricePriorityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceQueryService implements PriceQueryUseCase {

    private final PriceQueryRepository priceQueryRepository;
    private final DateConverter dateConverter;
    private final PricePriorityService pricePriorityService;

    public PriceQueryService(PriceQueryRepository priceQueryRepository,
                             DateConverter dateConverter,
                             PricePriorityService pricePriorityService) {
        this.priceQueryRepository = priceQueryRepository;
        this.dateConverter = dateConverter;
        this.pricePriorityService = pricePriorityService;
    }

    @Override
    public PriceQuery priceQuery(Long brandId, Long productId, String applicationDate)
            throws InternalServerErrorException, NotDataFoundException, PriceConfigurationErrorException {
        List<PriceQuery> prices = this.priceQueryRepository.findPrices(
                productId, brandId, this.dateConverter.toLocalDateTime(applicationDate)
        );

        if (prices.isEmpty()) {
            throw new NotDataFoundException();
        }

        if (prices.size() > 1 ) {
            return this.pricePriorityService.getHighestPriorityPrice(prices);
        }

        return prices.get(0);
    }
}
