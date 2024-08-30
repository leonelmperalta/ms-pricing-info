package com.lperalta.ms.pricing.info.prices.infraestructure.out.database.mapper;

import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.dbo.JPAPriceQueryDAO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JPAPriceQueryDAOMapper {

    JPAPriceQueryDAOMapper INSTANCE = Mappers.getMapper(JPAPriceQueryDAOMapper.class);

    PriceQuery toPriceQuery(JPAPriceQueryDAO jpaPriceQueryDAO);
}
