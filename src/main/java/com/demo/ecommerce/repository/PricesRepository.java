package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PricesRepository extends JpaRepository<PricesEntity, Integer> {

    @Query("SELECT p FROM PricesEntity p " +
            "WHERE p.productId = :productId " +
            "AND p.brand.id = :brandId " +
            "AND :date BETWEEN parsedatetime(p.startDate, 'yyyy-MM-dd-HH.mm.ss') AND parsedatetime(p.endDate, 'yyyy-MM-dd-HH.mm.ss')")
    Optional<PricesEntity> findPricesByProductIdAndBrandIdAndDate(
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId,
            @Param("date") LocalDateTime date);


    Optional<List<PricesEntity>> findByBrandId(int branId);

}
