package com.kte.ems.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record DataLoadRequest(
        @Min(1) @Max(5_000_000) int targetCount,
        @Min(100) @Max(50_000) int batchSize
) {}
