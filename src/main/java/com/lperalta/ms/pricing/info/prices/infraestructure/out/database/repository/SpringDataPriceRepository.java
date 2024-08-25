package com.lperalta.ms.pricing.info.prices.infraestructure.out.database.repository;

import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.dbo.SpringDataPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<SpringDataPriceEntity, Long> {
    @Query("SELECT e FROM SpringDataPriceEntity e " +
            "WHERE e.productId = :productId " +
            "AND e.brandId = :brandId " +
            "AND e.applicationStartDate < :applicationDate " +
            "AND e.applicationEndDate > :applicationDate")
    List<SpringDataPriceEntity> findByProductIdAndBrandIdAndApplicationDatesBetween(
            Long productId, Long brandId, LocalDateTime applicationDate
    );
}
