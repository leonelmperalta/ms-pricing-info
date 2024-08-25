package com.lperalta.ms.pricing.info.prices.infraestructure.out.database.mapper;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.dbo.SpringDataPriceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringDataPriceQueryMapper {

    public List<PriceQuery> toPriceQuery(List<SpringDataPriceEntity> priceEntities) {
        return priceEntities.stream().map(this::toPriceQuery).toList();
    }

    public PriceQuery toPriceQuery(SpringDataPriceEntity springDataPriceEntity) {
        return PriceQuery.builder()
                .applicationEndDate(springDataPriceEntity.getApplicationEndDate())
                .applicationStartDate(springDataPriceEntity.getApplicationStartDate())
                .brandId(springDataPriceEntity.getBrandId())
                .currency(springDataPriceEntity.getCurrency())
                .feeId(springDataPriceEntity.getFeeId())
                .finalPrice(springDataPriceEntity.getFinalPrice())
                .priority(springDataPriceEntity.getPriority())
                .productId(springDataPriceEntity.getProductId())
                .build();
    }
}
