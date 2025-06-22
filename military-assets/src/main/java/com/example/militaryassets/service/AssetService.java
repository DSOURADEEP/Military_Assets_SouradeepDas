package com.example.militaryassets.service;

import com.example.militaryassets.dto.AssetRequest;
import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset addAsset(AssetRequest request) {
        Asset asset = new Asset();
        asset.setName(request.getName());
        asset.setType(request.getType());
        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
}