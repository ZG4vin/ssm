package com.gavin.webservice.utils;

import com.gavin.bean.Weather;
import com.gavin.webservice.ArrayOfString;
import com.gavin.webservice.WeatherWebService;

import java.util.ArrayList;
import java.util.List;

public class WeatherUtils {
    /**
     * 根据城市名，获取最近三天的天气
     * @param aos 返回的数据
     * @param cityName 市名
     * @return
     */
    public static List<Weather> getThreeDayWeather(ArrayOfString aos,String cityName){
        List<Weather> weatherList=new ArrayList<Weather>();

        //如果用户填进去市  去掉 '市'
        String newCityName=cityName.replace("市","");
        WeatherWebService weatherWebService=new WeatherWebService();
        ArrayOfString city = weatherWebService.getWeatherWebServiceSoap12().getWeatherbyCityName(newCityName);
        List<String> cityData = city.getString();

        //存放三天温度的索引
        int[] temperatureIndex={5,12,17};

        for (int i:temperatureIndex){
            String[] strings = cityData.get(i).split("/");
            //后一个索引存放的是日期
            String date = cityData.get(i + 1);

            Weather weather=new Weather();
            int min=Integer.parseInt(strings[0].substring(0,strings[0].indexOf("℃")));
            weather.setcMin(min);
            int max=Integer.parseInt(strings[1].substring(0,strings[0].indexOf("℃")));
            weather.setcMax(max);
            weather.setDay(date);

            weatherList.add(weather);
        }

        return weatherList;
    }
}
