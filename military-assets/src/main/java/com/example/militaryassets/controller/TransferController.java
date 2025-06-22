package com.example.militaryassets.controller;

import com.example.militaryassets.dto.TransferRequest;
import com.example.militaryassets.entity.Transfer;
import com.example.militaryassets.service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public Transfer performTransfer(@RequestBody TransferRequest request) {
        return transferService.transferAsset(request);
    }

    @GetMapping
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }
}


