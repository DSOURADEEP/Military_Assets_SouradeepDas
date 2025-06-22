
package com.example.militaryassets.controller;

import com.example.militaryassets.dto.AssignmentRequest;
import com.example.militaryassets.entity.Assignment;
import com.example.militaryassets.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public Assignment createAssignment(@RequestBody AssignmentRequest request) {
        return assignmentService.assignAsset(request);
    }

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }
}
