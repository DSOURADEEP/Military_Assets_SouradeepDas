package com.example.militaryassets.controller;

import com.example.militaryassets.dto.PurchaseRequest;
import com.example.militaryassets.entity.Purchase;
import com.example.militaryassets.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    private final PurchaseService svc;
    public PurchaseController(PurchaseService svc) { this.svc = svc; }

    @PostMapping
    public Purchase record(@RequestBody PurchaseRequest r) {
        return svc.record(r);
    }

    @GetMapping
    public List<Purchase> list() {
        return svc.getAll();
    }
}

