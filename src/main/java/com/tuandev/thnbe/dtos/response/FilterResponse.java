package com.tuandev.thnbe.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilterResponse {
    private Long filterId;

    private String name;

    private double blur;

    private double brightness;

    private double contrast;

    private double grayscale;

    private double hueRotate;

    private double invert;

    private double opacity;

    private double saturate;

    private double sepia;
}
