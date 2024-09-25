package com.tuandev.thnbe.mappers;

import com.tuandev.thnbe.dtos.request.FilterRequest;
import com.tuandev.thnbe.dtos.response.FilterResponse;
import com.tuandev.thnbe.entities.Filter;

public class FilterMapper {
    public static Filter mapperToFilter(FilterRequest filterRequest) {
        return Filter.builder()
                .name(filterRequest.getName())
                .blur(filterRequest.getBlur())
                .brightness(filterRequest.getBrightness())
                .contrast(filterRequest.getContrast())
                .grayscale(filterRequest.getGrayscale())
                .hueRotate(filterRequest.getHueRotate())
                .invert(filterRequest.getInvert())
                .opacity(filterRequest.getOpacity())
                .saturate(filterRequest.getSaturate())
                .sepia(filterRequest.getSepia())
                .build();
    }

    public static FilterResponse mapperToFilterResponse(Filter filter) {
        return FilterResponse.builder()
                .filterId(filter.getFilterId())
                .name(filter.getName())
                .blur(filter.getBlur())
                .brightness(filter.getBrightness())
                .contrast(filter.getContrast())
                .grayscale(filter.getGrayscale())
                .hueRotate(filter.getHueRotate())
                .invert(filter.getInvert())
                .opacity(filter.getOpacity())
                .saturate(filter.getSaturate())
                .sepia(filter.getSepia())
                .build();
    }
}
