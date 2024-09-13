package com.tuandev.thnbe.services.impl;

import com.tuandev.thnbe.entities.Country;
import com.tuandev.thnbe.repositories.CountryRepository;
import com.tuandev.thnbe.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;


    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryByCode(String code) {
        return countryRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Country not found"));
    }

}
