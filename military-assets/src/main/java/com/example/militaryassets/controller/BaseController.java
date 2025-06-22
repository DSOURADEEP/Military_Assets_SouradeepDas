package com.example.militaryassets.controller;

import com.example.militaryassets.entity.Base;
import com.example.militaryassets.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bases")
public class BaseController {

    @Autowired
    private BaseRepository baseRepository;

    @PostMapping
    public Base addBase(@RequestBody Base base) {
        return baseRepository.save(base);
    }

    @GetMapping
    public List<Base> getAllBases() {
        return baseRepository.findAll();
    }
}

