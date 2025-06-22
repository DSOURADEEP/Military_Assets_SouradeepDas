package com.example.militaryassets.service;

import com.example.militaryassets.dto.DashboardResponse;
import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final AssetRepository assetRepository;
    private final PurchaseRepository purchaseRepository;
    private final TransferRepository transferRepository;
    private final AssignmentRepository assignmentRepository;
    private final ExpenditureRepository expenditureRepository;

    public DashboardService(
            AssetRepository assetRepository,
            PurchaseRepository purchaseRepository,
            TransferRepository transferRepository,
            AssignmentRepository assignmentRepository,
            ExpenditureRepository expenditureRepository
    ) {
        this.assetRepository = assetRepository;
        this.purchaseRepository = purchaseRepository;
        this.transferRepository = transferRepository;
        this.assignmentRepository = assignmentRepository;
        this.expenditureRepository = expenditureRepository;
    }

    public List<DashboardResponse> getDashboardData(Long baseId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Asset> assets = assetRepository.findAll();
        List<DashboardResponse> responses = new ArrayList<>();

        for (Asset asset : assets) {
            int purchases = purchaseRepository.sumQuantityByBaseAndAssetAndDateRange(baseId, asset.getId(), startDate, endDate);
            int transferIn = transferRepository.sumTransferInByBaseAndAsset(baseId, asset.getId(), startDate, endDate);
            int transferOut = transferRepository.sumTransferOutByBaseAndAsset(baseId, asset.getId(), startDate, endDate);
            int assignments = assignmentRepository.sumQuantityByBaseAndAssetAndDateRange(baseId, asset.getId(), startDate, endDate);
            int expenditures = expenditureRepository.sumQuantityByBaseAndAssetAndDateRange(baseId, asset.getId(), startDate, endDate);

            int openingBalance = 0;
            int closingBalance = openingBalance + purchases + transferIn - transferOut - assignments - expenditures;

            responses.add(new DashboardResponse(
                    asset.getName(), asset.getType(),
                    openingBalance, purchases, transferIn,
                    transferOut, assignments, expenditures, closingBalance
            ));
        }

        return responses;
    }
}

