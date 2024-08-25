package com.lperalta.ms.pricing.info.prices.domain.repository;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceQueryRepositoryPort {
    List<PriceQuery> findByProductIdAndBrandIdAndApplicationDatesBetween(
            Long productId, Long brandId, LocalDateTime applicationDate
    );
}
