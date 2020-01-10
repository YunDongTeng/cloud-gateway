package com.cloud.gateway.entity;

public class SysServerParam extends BaseEntity {

    private Integer id;

    private String apiParamName;

    private Integer apiServerId;

    private String name;

    private String position;

    private String requestType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiParamName() {
        return apiParamName;
    }

    public void setApiParamName(String apiParamName) {
        this.apiParamName = apiParamName;
    }

    public Integer getApiServerId() {
        return apiServerId;
    }

    public void setApiServerId(Integer apiServerId) {
        this.apiServerId = apiServerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
