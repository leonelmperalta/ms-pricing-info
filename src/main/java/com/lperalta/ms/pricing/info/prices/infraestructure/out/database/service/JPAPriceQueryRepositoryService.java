package com.lperalta.ms.pricing.info.prices.infraestructure.out.database.service;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.domain.port.out.PriceQueryRepository;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.dbo.JPAPriceQueryDAO;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.mapper.JPAPriceQueryMapper;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.repository.JPAPriceQueryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JPAPriceQueryRepositoryService implements PriceQueryRepository {

    private final JPAPriceQueryRepository priceRepository;
    private final JPAPriceQueryMapper priceQueryMapper;

    public JPAPriceQueryRepositoryService(JPAPriceQueryRepository priceRepository,
                                          JPAPriceQueryMapper priceQueryMapper) {
        this.priceRepository = priceRepository;
        this.priceQueryMapper = priceQueryMapper;
    }

    @Override
    public List<PriceQuery> findPrices(
            Long productId, Long brandId, LocalDateTime applicationDate
    ) {
        List<JPAPriceQueryDAO> priceEntities = this.priceRepository.findByProductIdAndBrandIdAndApplicationDatesBetween(
                productId, brandId, applicationDate
        );
        return priceEntities.stream().map(this.priceQueryMapper::toPriceQuery).toList();
    }
}
