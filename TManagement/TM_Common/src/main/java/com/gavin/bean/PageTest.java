package com.gavin.bean;



import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageTest<T> {
    private PageInfo<T> pageInfo;
    private List<T> list;

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
