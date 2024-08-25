package com.lperalta.ms.pricing.info.prices.infraestructure.out.database.repository;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.domain.repository.PriceQueryRepositoryPort;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.dbo.SpringDataPriceEntity;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.mapper.SpringDataPriceQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpringDataPriceQueryRepository implements PriceQueryRepositoryPort {

    private final SpringDataPriceRepository priceRepository;
    private final SpringDataPriceQueryMapper springDataPriceQueryMapper;

    @Autowired
    public SpringDataPriceQueryRepository(SpringDataPriceRepository priceRepository,
                                          SpringDataPriceQueryMapper springDataPriceQueryMapper) {
        this.priceRepository = priceRepository;
        this.springDataPriceQueryMapper = springDataPriceQueryMapper;
    }

    @Override
    public List<PriceQuery> findByProductIdAndBrandIdAndApplicationDatesBetween(
            Long productId, Long brandId, LocalDateTime applicationDate
    ) {
        List<SpringDataPriceEntity> priceEntities = this.priceRepository.findByProductIdAndBrandIdAndApplicationDatesBetween(
                productId, brandId, applicationDate
        );
        return this.springDataPriceQueryMapper.toPriceQuery(priceEntities);
    }
}
