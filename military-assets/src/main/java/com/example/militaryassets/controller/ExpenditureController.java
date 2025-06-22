package com.example.militaryassets.controller;

import com.example.militaryassets.dto.ExpenditureRequest;
import com.example.militaryassets.entity.Expenditure;
import com.example.militaryassets.service.ExpenditureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenditures")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    public ExpenditureController(ExpenditureService expenditureService) {
        this.expenditureService = expenditureService;
    }

    @PostMapping
    public Expenditure recordExpenditure(@RequestBody ExpenditureRequest request) {
        return expenditureService.recordExpenditure(request);
    }

    @GetMapping
    public List<Expenditure> getAll() {
        return expenditureService.getAllExpenditures();
    }
}