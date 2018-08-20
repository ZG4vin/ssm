package com.gavin.controller;

import com.gavin.bean.*;
import com.gavin.serivce.GroupService;
import com.gavin.serivce.ImgService;
import com.gavin.serivce.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.gavin.serivce.CityService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private MenuService menuService;


    @RequestMapping
    public String InitIndex(){
        return "index";
    }

    @GetMapping("/showEcharts/{cityName}")
    @ResponseBody
    public Option ShowEcharts(@PathVariable("cityName") String cityName){
//        WeatherWebService weatherWebService=new WeatherWebService();
//        ArrayOfString city = weatherWebService.getWeatherWebServiceSoap().getWeatherbyCityName("淄博");
//        List<Weather> dayWeather = WeatherUtils.getThreeDayWeather(city, cityName);
//
//        Option option=new Option();
//        XAxis xAxis=new XAxis();
//        List<Series> seriesList=new ArrayList<>();
//
//        //最小值List
//        List<Integer> minList=new ArrayList<>();
//
//        //最大值List
//        List<Integer> maxList=new ArrayList<>();
//
//        //日期
//        List<String> dateList=new ArrayList<>();
//
//
//        for (Weather weather:dayWeather){
//            dateList.add(weather.getDay());
//            minList.add(weather.getcMin());
//            maxList.add(weather.getcMax());
//        }
//        xAxis.setData(dateList);
//
//        Series seriesMin=new Series(minList);
//        Series seriesMax=new Series(maxList);
//        seriesList.add(seriesMin);
//        seriesList.add(seriesMax);
//
//        option.setxAxis(xAxis);
//        option.setSeries(seriesList);

        Option option=new Option();

        XAxis xAxis=new XAxis();
        List<String> dayList=new ArrayList<>();
        dayList.add("星期一");
        dayList.add("星期二");
        dayList.add("星期三");
        dayList.add("星期四");
        dayList.add("星期五");
        dayList.add("星期六");
        dayList.add("星期日");
        xAxis.setData(dayList);

        //最小值List
        List<Integer> minList=new ArrayList<>();
        minList.add(21);
        minList.add(73);
        minList.add(41);
        minList.add(201);
        minList.add(58);
        minList.add(174);
        minList.add(34);

        //最大值List
        List<Integer> maxList=new ArrayList<>();
        maxList.add(235);
        maxList.add(177);
        maxList.add(287);
        maxList.add(98);
        maxList.add(136);
        maxList.add(57);
        maxList.add(71);

        List<Series> seriesList=new ArrayList<>();
        Series seriesMin=new Series(minList);
        Series seriesMax=new Series(maxList);
        seriesList.add(seriesMin);
        seriesList.add(seriesMax);

        option.setxAxis(xAxis);
        option.setSeries(seriesList);

        return option;
    }

    @GetMapping("/initTree")
    @ResponseBody
    public List<City> InitTree(){
        List<City> allCity = cityService.getAllCity();
        return allCity;
    }

    @PutMapping("/addTreeNode/{cityName}")
    @ResponseBody
    public String addTreeNode(@PathVariable("cityName")String cityName,String selectId){
        City city=new City();
        city.setCityName(cityName);
        city.setpId(Integer.parseInt(selectId));
        cityService.insert(city);
        return "ok";
    }


    @PutMapping("/modifyTreeNode/{cityId}")
    @ResponseBody
    public String ModifyTreeNode(@PathVariable("cityId")String cityId,String cityName){
        City city = cityService.getCityById(cityId);
        city.setCityName(cityName);
        cityService.updateCity(city);
        return "ok";
    }

    @DeleteMapping("deleteTreeNode/{cityId}")
    @ResponseBody
    public String deleteTreeNode(@PathVariable("cityId")String cityId){
        cityService.deleteCity(cityId);
        cityService.deleteCityByPid(cityId);
        return "ok";
    }


    @PutMapping("/{treeNodeId}/{targetNodeId}/{moveType}")
    @ResponseBody
    public String moveTreeNode(@PathVariable("treeNodeId")String treeNodeId,@PathVariable("targetNodeId")String targetNodeId,@PathVariable("moveType")String moveType){
        City city = cityService.getCityById(treeNodeId);
        city.setpId(Integer.parseInt(targetNodeId));
        cityService.updateCity(city);
        return "ok";
    }

    @PostMapping("/img")
    public String img(Img img, RedirectAttributes attr) throws IOException {


        MultipartFile imgFile = img.getImgFile();
        String fileName=System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
        File file = new File("/Users/gavin/TM_Img/" + fileName);
        File fileFolder = new File("/Users/gavin/TM_Img/");
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        imgFile.transferTo(file);

        Img img1=new Img();
        img1.setImgName(fileName);
        imgService.insert(img1);

        attr.addFlashAttribute("imgURL", fileName);
        return "redirect:/index";
    }

    @DeleteMapping("/removeCache")
    @ResponseBody
    public String removeCache(){
        imgService.delete();
        File file=new File("/Users/gavin/TM_Img");
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                f.delete();
            }
        }
        return "ok";
    }

    @GetMapping("/initMenuTree/{groupId}")
    @ResponseBody
    public List<Menus> InitMenuTree(@PathVariable("groupId") String groupId){
        List<GroupMenu> groupMenu = groupService.getGroupMenu(groupId);
        List<Menus> list=new ArrayList<>();
        for (GroupMenu gm:groupMenu){
            Menus menu = menuService.getMenu(gm.getMenuId().toString());
            list.add(menu);
        }
        return list;
    }
}
