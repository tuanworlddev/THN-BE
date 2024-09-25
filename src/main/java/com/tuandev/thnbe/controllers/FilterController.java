package com.tuandev.thnbe.controllers;

import com.tuandev.thnbe.dtos.request.FilterRequest;
import com.tuandev.thnbe.dtos.response.FilterResponse;
import com.tuandev.thnbe.services.FilterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/filters")
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @GetMapping
    public ResponseEntity<List<FilterResponse>> getAllFilters() {
        return ResponseEntity.ok(filterService.getAllFilters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilterResponse> getFilterById(@PathVariable Long id) {
        return ResponseEntity.ok(filterService.getFilterById(id));
    }

    @PostMapping
    public ResponseEntity<FilterResponse> addFilter(@Valid @RequestBody FilterRequest filterRequest) {
        System.out.println(filterRequest);
        return ResponseEntity.ok(filterService.saveFilter(filterRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilter(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("message", "Filter deleted successfully"));
    }
}
