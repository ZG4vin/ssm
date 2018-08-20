package com.gavin.bean;

import java.util.List;

public class Series {
    private List<Integer> data;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public Series(List<Integer> data) {
        this.data = data;
    }
    public Series(){

    }

    public String type="line";

    public boolean sommth=true;

}
