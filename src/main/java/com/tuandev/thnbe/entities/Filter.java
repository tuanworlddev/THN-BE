package com.tuandev.thnbe.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filter_id")
    private Long filterId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double blur;

    @Column(nullable = false)
    private double brightness;

    @Column(nullable = false)
    private double contrast;

    @Column(nullable = false)
    private double grayscale;

    @Column(name = "hue_rotate", nullable = false)
    private double hueRotate;

    @Column(nullable = false)
    private double invert;

    @Column(nullable = false)
    private double opacity;

    @Column(nullable = false)
    private double saturate;

    @Column(nullable = false)
    private double sepia;

    @PrePersist
    protected void onCreate() {
        blur = 0;
        brightness = 1;
        contrast = 1;
        grayscale = 0;
        hueRotate = 0;
        invert = 0;
        opacity = 1;
        saturate = 1;
        sepia = 0;
    }
}

