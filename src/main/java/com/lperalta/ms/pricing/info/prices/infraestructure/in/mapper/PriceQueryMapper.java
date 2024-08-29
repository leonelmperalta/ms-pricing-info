package com.lperalta.ms.pricing.info.prices.infraestructure.in.mapper;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.in.dto.PriceQueryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceQueryMapper {

    PriceQueryMapper INSTANCE = Mappers.getMapper(PriceQueryMapper.class);

    @Mappings(value = {
            @Mapping(source = "applicationEndDate", target = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "applicationStartDate", target = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "finalPrice", target = "finalAmount", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    PriceQueryDTO toDto(PriceQuery priceQuery);
}
