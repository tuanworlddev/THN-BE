package com.tuandev.thnbe.services;

import com.tuandev.thnbe.entities.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryByCode(String code);
}
