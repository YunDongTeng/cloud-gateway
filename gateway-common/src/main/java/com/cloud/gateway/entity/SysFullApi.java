package com.cloud.gateway.entity;

import java.io.Serializable;
import java.util.List;

public class SysFullApi implements Serializable {

    private SysApi api;

    private List<SysApiParam> apiParams;

    private SysApiServer apiServer;

    private List<SysServerParam> serverParams;

    private SysServerResponse apiResponse;

    public SysApi getApi() {
        return api;
    }

    public void setApi(SysApi api) {
        this.api = api;
    }

    public List<SysApiParam> getApiParams() {
        return apiParams;
    }

    public void setApiParams(List<SysApiParam> apiParams) {
        this.apiParams = apiParams;
    }

    public List<SysServerParam> getServerParams() {
        return serverParams;
    }

    public void setServerParams(List<SysServerParam> serverParams) {
        this.serverParams = serverParams;
    }

    public SysApiServer getApiServer() {
        return apiServer;
    }

    public void setApiServer(SysApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public SysServerResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(SysServerResponse apiResponse) {
        this.apiResponse = apiResponse;
    }
}
