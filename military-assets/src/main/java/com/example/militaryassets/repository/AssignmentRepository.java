package com.example.militaryassets.repository;

import com.example.militaryassets.entity.Assignment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    @Query("SELECT COALESCE(SUM(a.quantity), 0) FROM Assignment a " +
            "WHERE a.base.id = :baseId AND a.asset.id = :assetId AND a.date BETWEEN :start AND :end")
    int sumQuantityByBaseAndAssetAndDateRange(Long baseId, Long assetId, LocalDateTime start, LocalDateTime end);
}

