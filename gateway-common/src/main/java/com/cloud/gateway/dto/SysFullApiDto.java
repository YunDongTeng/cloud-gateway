package com.cloud.gateway.dto;

import java.util.List;

public class SysFullApiDto {

    private SysApiDto api;

    private SysApiServerDto apiServer;

    private List<SysApiParamDto> apiParamList;

    private List<SysServerParamDto> serverParamList;

    private SysServerResponseDto serverResponse;

    public SysApiDto getApi() {
        return api;
    }

    public void setApi(SysApiDto api) {
        this.api = api;
    }

    public SysApiServerDto getApiServer() {
        return apiServer;
    }

    public void setApiServer(SysApiServerDto apiServer) {
        this.apiServer = apiServer;
    }

    public List<SysApiParamDto> getApiParamList() {
        return apiParamList;
    }

    public void setApiParamList(List<SysApiParamDto> apiParamList) {
        this.apiParamList = apiParamList;
    }

    public List<SysServerParamDto> getServerParamList() {
        return serverParamList;
    }

    public void setServerParamList(List<SysServerParamDto> serverParamList) {
        this.serverParamList = serverParamList;
    }

    public SysServerResponseDto getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(SysServerResponseDto serverResponse) {
        this.serverResponse = serverResponse;
    }
}
