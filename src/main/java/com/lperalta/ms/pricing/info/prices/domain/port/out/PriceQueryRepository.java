package com.lperalta.ms.pricing.info.prices.domain.port.out;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceQueryRepository {
    List<PriceQuery> findPrices(
            Long productId, Long brandId, LocalDateTime applicationDate
    );
}
