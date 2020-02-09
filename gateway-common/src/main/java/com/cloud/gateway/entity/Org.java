package com.cloud.gateway.entity;

public class Org {

    private String id;
    private String name;

    private String code;

    private String parentId;

    public Org() {
    }

    public Org(String id, String name, String code, String parentId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentId = parentId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
