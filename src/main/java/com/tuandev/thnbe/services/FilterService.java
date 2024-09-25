package com.tuandev.thnbe.services;

import com.tuandev.thnbe.dtos.request.FilterRequest;
import com.tuandev.thnbe.dtos.response.FilterResponse;

import java.util.List;

public interface FilterService {
    List<FilterResponse> getAllFilters();
    FilterResponse getFilterById(Long id);
    FilterResponse saveFilter(FilterRequest filterRequest);
    void deleteFilterById(Long id);
}
