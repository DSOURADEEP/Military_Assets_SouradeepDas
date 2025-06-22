package com.example.militaryassets.repository;

import com.example.militaryassets.entity.Expenditure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface ExpenditureRepository extends CrudRepository<Expenditure, Long> {

    @Query("SELECT COALESCE(SUM(e.quantity), 0) FROM Expenditure e " +
            "WHERE e.base.id = :baseId AND e.asset.id = :assetId AND e.date BETWEEN :start AND :end")
    int sumQuantityByBaseAndAssetAndDateRange(Long baseId, Long assetId, LocalDateTime start, LocalDateTime end);
}

