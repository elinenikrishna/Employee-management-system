package com.kte.ems.dto;

public record DataLoadResponse(long requestedRecords, long insertedRecords, long durationMs, String message) {}
