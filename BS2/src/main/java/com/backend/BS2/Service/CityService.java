package com.backend.BS2.Service;

import com.backend.BS2.model.City;

import java.util.List;

public interface CityService {
    boolean addCity(City city);
    List<City> getCities();
}
