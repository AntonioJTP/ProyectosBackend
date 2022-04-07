package com.backend.BS2.Service;

import com.backend.BS2.model.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImp implements CityService {
    List<City> cities = new ArrayList();

    @Override
    public List<City> getCities() {
        return cities;
    }

    @Override
    public boolean addCity(City city) {
        return cities.add(city);
    }
}
