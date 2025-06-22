package com.example.militaryassets.service;

import com.example.militaryassets.dto.PurchaseRequest;
import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.entity.Base;
import com.example.militaryassets.entity.Purchase;
import com.example.militaryassets.repository.AssetRepository;
import com.example.militaryassets.repository.BaseRepository;
import com.example.militaryassets.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BaseRepository baseRepository;
    private final AssetRepository assetRepository;

    public PurchaseService(PurchaseRepository purchaseRepository,
                           BaseRepository baseRepository,
                           AssetRepository assetRepository) {
        this.purchaseRepository = purchaseRepository;
        this.baseRepository = baseRepository;
        this.assetRepository = assetRepository;
    }

    public Purchase record(PurchaseRequest request) {
        Base base = baseRepository.findById(request.getBaseId()).orElseThrow();
        Asset asset = assetRepository.findById(request.getAssetId()).orElseThrow();

        Purchase purchase = new Purchase();
        purchase.setBase(base);
        purchase.setAsset(asset);
        purchase.setQuantity(request.getQuantity());
        purchase.setDate(request.getDate());

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAll() {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}


