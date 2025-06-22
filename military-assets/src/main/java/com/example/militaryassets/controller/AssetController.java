package com.example.militaryassets.controller;

import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @PostMapping
    public Asset addAsset(@RequestBody Asset asset) {
        return assetRepository.save(asset);
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
}