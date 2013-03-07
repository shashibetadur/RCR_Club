package com.rcr.web.model;


import java.util.List;

public class ListView<T> {

    private List<T> list;

    public ListView(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }
}
