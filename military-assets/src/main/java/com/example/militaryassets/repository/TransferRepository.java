package com.example.militaryassets.repository;

import com.example.militaryassets.entity.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TransferRepository extends CrudRepository<Transfer, Long> {

    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM Transfer t " +
            "WHERE t.toBase.id = :baseId AND t.asset.id = :assetId AND t.date BETWEEN :start AND :end")
    int sumTransferInByBaseAndAsset(@Param("baseId") Long baseId,
                                    @Param("assetId") Long assetId,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM Transfer t " +
            "WHERE t.fromBase.id = :baseId AND t.asset.id = :assetId AND t.date BETWEEN :start AND :end")
    int sumTransferOutByBaseAndAsset(@Param("baseId") Long baseId,
                                     @Param("assetId") Long assetId,
                                     @Param("start") LocalDateTime start,
                                     @Param("end") LocalDateTime end);
}


