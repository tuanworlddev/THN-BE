package com.tuandev.thnbe.services.impl;

import com.tuandev.thnbe.dtos.request.FilterRequest;
import com.tuandev.thnbe.dtos.response.FilterResponse;
import com.tuandev.thnbe.entities.Filter;
import com.tuandev.thnbe.mappers.FilterMapper;
import com.tuandev.thnbe.repositories.FilterRepository;
import com.tuandev.thnbe.services.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {
    private final FilterRepository filterRepository;

    @Override
    public List<FilterResponse> getAllFilters() {
        return filterRepository.findAll().stream().map(FilterMapper::mapperToFilterResponse).toList();
    }

    @Override
    public FilterResponse getFilterById(Long id) {
        return filterRepository.findById(id).map(FilterMapper::mapperToFilterResponse).orElseThrow(() -> new RuntimeException("Filter not found"));
    }

    @Override
    public FilterResponse saveFilter(FilterRequest filterRequest) {
        Filter filter = filterRepository.save(FilterMapper.mapperToFilter(filterRequest));
        return FilterMapper.mapperToFilterResponse(filter);
    }

    @Override
    public void deleteFilterById(Long id) {
        if (!filterRepository.existsById(id)) {
            throw new RuntimeException("Filter not found");
        }
        filterRepository.deleteById(id);
    }
}
