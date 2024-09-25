package com.tuandev.thnbe.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterRequest {

    @NotNull
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
