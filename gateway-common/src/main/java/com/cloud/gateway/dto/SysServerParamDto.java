package com.cloud.gateway.dto;

public class SysServerParamDto {

    private String name;

    private String apiParamName;

    private String position;

    private String requestType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiParamName() {
        return apiParamName;
    }

    public void setApiParamName(String apiParamName) {
        this.apiParamName = apiParamName;
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
