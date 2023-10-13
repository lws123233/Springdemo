package com.example.springdemo.pojo;

import java.io.Serializable;

public class Data implements Serializable {

    private static final long serialVersionUID = -5518745916220905093L;
    private String id;
    private String name;
    private String price;
    private String remark;

    public Data() {
    }

    public Data(String id, String name, String price, String remark) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
