package com.bkbk.entity.vo;

public class ClientInfo {
    private  String avatatUrl;
    private  String name;
    private  Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientInfo() {
    }

    public String getAvatatUrl() {
        return avatatUrl;
    }

    public void setAvatatUrl(String avatatUrl) {
        this.avatatUrl = avatatUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
