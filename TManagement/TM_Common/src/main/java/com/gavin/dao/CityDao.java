package com.gavin.dao;

import com.gavin.bean.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface CityDao {
    @Select("select * from city")
    List<City> getAllCity();

    @Insert("insert into city(cityname,pid) values(#{cityName},#{pId})")
    void insert(City city);

    @Select("select * from city where id=#{cityId}")
    City getCityById(String cityId);

    @Update("update city set cityname=#{cityName},pid=#{pId} where id=#{id}")
    void updateCity(City city);

    @Delete("delete from city where id=#{cityId}")
    void deleteCity(String cityId);

    @Delete("delete from city where pid=#{cityId}")
    void deleteCityByPid(String cityId);
}
