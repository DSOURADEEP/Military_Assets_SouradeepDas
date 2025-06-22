package com.example.militaryassets.repository;

import com.example.militaryassets.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query("SELECT COALESCE(SUM(p.quantity), 0) FROM Purchase p WHERE p.base.id = :baseId AND p.asset.id = :assetId AND p.date BETWEEN :start AND :end")
    int sumQuantityByBaseAndAssetAndDateRange(Long baseId, Long assetId, LocalDateTime start, LocalDateTime end);
}


