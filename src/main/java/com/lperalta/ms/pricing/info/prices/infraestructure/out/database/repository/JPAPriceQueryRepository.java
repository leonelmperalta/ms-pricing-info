package com.lperalta.ms.pricing.info.prices.infraestructure.out.database.repository;

import com.lperalta.ms.pricing.info.prices.infraestructure.out.database.dbo.JPAPriceQueryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JPAPriceQueryRepository extends JpaRepository<JPAPriceQueryDAO, Long> {
    @Query("SELECT e FROM JPAPriceQueryDAO e " +
            "WHERE e.productId = :productId " +
            "AND e.brandId = :brandId " +
            "AND e.applicationStartDate < :applicationDate " +
            "AND e.applicationEndDate > :applicationDate")
    List<JPAPriceQueryDAO> findByProductIdAndBrandIdAndApplicationDatesBetween(
            Long productId, Long brandId, LocalDateTime applicationDate
    );
}
