package com.gavin.serivce.impl;


import com.gavin.bean.City;
import com.gavin.dao.CityDao;
import com.gavin.serivce.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> getAllCity() {
        return cityDao.getAllCity();
    }

    public void insert(City city) {
        cityDao.insert(city);
    }

    public City getCityById(String cityId) {
        return cityDao.getCityById(cityId);
    }

    public void updateCity(City city) {
        cityDao.updateCity(city);
    }

    public void deleteCity(String cityId) {
        cityDao.deleteCity(cityId);
    }

    public void deleteCityByPid(String cityId) {
        cityDao.deleteCityByPid(cityId);
    }
}
