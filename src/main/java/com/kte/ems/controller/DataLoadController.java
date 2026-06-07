package com.kte.ems.controller;

import com.kte.ems.dto.DataLoadRequest;
import com.kte.ems.dto.DataLoadResponse;
import com.kte.ems.service.DataLoadService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/data-loader")
@Profile({"local", "dev"})
public class DataLoadController {
    private final DataLoadService dataLoadService;

    public DataLoadController(DataLoadService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }

    @PostMapping("/employees")
    public DataLoadResponse generateEmployees(@Valid @RequestBody DataLoadRequest request) {
        return dataLoadService.generateEmployees(request.targetCount(), request.batchSize());
    }
}
