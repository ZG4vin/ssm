package com.gavin.serivce;

import com.gavin.bean.City;

import java.util.List;


public interface CityService {
    List<City> getAllCity();

    void insert(City city);

    City getCityById(String cityId);

    void updateCity(City city);

    void deleteCity(String cityId);

    void deleteCityByPid(String cityId);
}
