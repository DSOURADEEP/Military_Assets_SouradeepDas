package com.example.militaryassets.service;

import com.example.militaryassets.dto.AssignmentRequest;
import com.example.militaryassets.entity.Asset;
import com.example.militaryassets.entity.Assignment;
import com.example.militaryassets.entity.Base;
import com.example.militaryassets.repository.AssetRepository;
import com.example.militaryassets.repository.AssignmentRepository;
import com.example.militaryassets.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AssetRepository assetRepository;
    private final BaseRepository baseRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             AssetRepository assetRepository,
                             BaseRepository baseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assetRepository = assetRepository;
        this.baseRepository = baseRepository;
    }

    public Assignment assignAsset(AssignmentRequest request) {
        Asset asset = assetRepository.findById(request.getAssetId()).orElseThrow();
        Base base = baseRepository.findById(request.getBaseId()).orElseThrow();

        Assignment assignment = new Assignment();
        assignment.setAsset(asset);
        assignment.setBase(base);
        assignment.setPersonnelName(request.getPersonnelName());
        assignment.setQuantity(request.getQuantity());
        assignment.setDate(request.getDate());

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return StreamSupport.stream(assignmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}


