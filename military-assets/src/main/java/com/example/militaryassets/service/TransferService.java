package com.example.militaryassets.service;

import com.example.militaryassets.dto.TransferRequest;
import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.entity.Base;
import com.example.militaryassets.entity.Transfer;
import com.example.militaryassets.repository.AssetRepository;
import com.example.militaryassets.repository.BaseRepository;
import com.example.militaryassets.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final BaseRepository baseRepository;
    private final AssetRepository assetRepository;

    public TransferService(TransferRepository transferRepository,
                           BaseRepository baseRepository,
                           AssetRepository assetRepository) {
        this.transferRepository = transferRepository;
        this.baseRepository = baseRepository;
        this.assetRepository = assetRepository;
    }

    public Transfer transferAsset(TransferRequest request) {
        Base fromBase = baseRepository.findById(request.getFromBaseId()).orElseThrow();
        Base toBase = baseRepository.findById(request.getToBaseId()).orElseThrow();
        Asset asset = assetRepository.findById(request.getAssetId()).orElseThrow();

        Transfer transfer = new Transfer();
        transfer.setFromBase(fromBase);
        transfer.setToBase(toBase);
        transfer.setAsset(asset);
        transfer.setQuantity(request.getQuantity());
        transfer.setDate(LocalDateTime.now());

        return transferRepository.save(transfer);
    }

    public List<Transfer> getAllTransfers() {
        return StreamSupport.stream(transferRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}



