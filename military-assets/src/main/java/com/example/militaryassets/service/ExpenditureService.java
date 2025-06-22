package com.example.militaryassets.service;

import com.example.militaryassets.dto.ExpenditureRequest;
import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.entity.Base;
import com.example.militaryassets.entity.Expenditure;
import com.example.militaryassets.repository.AssetRepository;
import com.example.militaryassets.repository.BaseRepository;
import com.example.militaryassets.repository.ExpenditureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExpenditureService {
    private final ExpenditureRepository expenditureRepository;
    private final BaseRepository baseRepository;
    private final AssetRepository assetRepository;

    public ExpenditureService(ExpenditureRepository expenditureRepository,
                              BaseRepository baseRepository,
                              AssetRepository assetRepository) {
        this.expenditureRepository = expenditureRepository;
        this.baseRepository = baseRepository;
        this.assetRepository = assetRepository;
    }

    public Expenditure recordExpenditure(ExpenditureRequest request) {
        Base base = baseRepository.findById(request.getBaseId()).orElseThrow();
        Asset asset = assetRepository.findById(request.getAssetId()).orElseThrow();

        Expenditure expenditure = new Expenditure();
        expenditure.setBase(base);
        expenditure.setAsset(asset);
        expenditure.setQuantity(request.getQuantity());
        expenditure.setReason(request.getReason());
        expenditure.setDate(request.getDate());

        return expenditureRepository.save(expenditure);
    }

    public List<Expenditure> getAllExpenditures() {
        return StreamSupport.stream(expenditureRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}

