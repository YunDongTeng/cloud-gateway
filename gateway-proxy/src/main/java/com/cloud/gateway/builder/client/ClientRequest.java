package com.cloud.gateway.builder.client;

import com.cloud.gateway.common.constant.ParamPositionEnum;
import com.cloud.gateway.common.context.http.ProxyHttpRequest;
import com.cloud.gateway.entity.SysApiParam;
import com.cloud.gateway.entity.SysFullApi;
import com.google.common.collect.Maps;
import io.netty.handler.codec.http.HttpHeaderNames;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientRequest {

    private String baseUrl;

    private String uri;

    private String contentType;

    private String method;

    private Map<String, String> headerParams;

    private Map<String, String> queryParams;

    public ClientRequest build(ProxyHttpRequest proxyHttpRequest, SysFullApi fullApi) {
        ClientRequest clientRequest = new ClientRequest();

        // 设置baseUrl
        clientRequest.setBaseUrl(fullApi.getApiServer().getRemoteUrl());
        // 设置URI
        clientRequest.setUri(fullApi.getApiServer().getUri());

        //设置method
        clientRequest.setMethod(fullApi.getApiServer().getMethod());

        // 设置查询参数
        clientRequest.setQueryParams(buildRequestParam(proxyHttpRequest, fullApi, ParamPositionEnum.REQUEST_PARAM));

        // 设置请求头参数
        clientRequest.setHeaderParams(buildHeaderParams(fullApi, buildRequestParam(proxyHttpRequest, fullApi, ParamPositionEnum.HEADER)));

        // 设置ContentType
        clientRequest.setContentType(fullApi.getApiServer().getContentType());

        return clientRequest;
    }


    public Map<String, String> buildHeaderParams(SysFullApi fullApi, Map<String, String> headerParams) {
        if (headerParams == null) {
            return null;
        }
        Map<String, String> headerMap = Maps.newHashMap();

        String contentType = fullApi.getApiServer().getContentType();


        // 设置内容类型
        headerMap.put(HttpHeaders.CONTENT_TYPE, contentType);

        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Connection", "keep-alive");

        // 设置Header参数
        for (Map.Entry<String, String> entry : headerParams.entrySet()) {
            if (entry.getValue() != null) {
                headerMap.put(entry.getKey(), entry.getValue());
            }
        }
        return headerMap;

    }

    public Map<String, String> buildRequestParam(ProxyHttpRequest proxyHttpRequest, SysFullApi fullApi, ParamPositionEnum... positionEnums) {


        Map<String, String> param = Maps.newHashMap();

        Map<String, SysApiParam> apiParamMap = fullApi.getApiParams().stream()
                .collect(Collectors.toMap(SysApiParam::getName, item -> item));

        fullApi.getServerParams().stream()

                // 过滤出需要填充的参数信息
                .filter(item -> {
                    for (ParamPositionEnum positionEnum : positionEnums) {
                        if (item.getPosition().equalsIgnoreCase(positionEnum.name())) {
                            return true;
                        }
                    }
                    return false;
                })
                .forEach(e -> {
                    switch (e.getPosition()) {
                        case "REQUEST_PARAM":
                            param.put(e.getName(), getValueFromRequest(proxyHttpRequest, apiParamMap.get(e.getApiParamName())));
                            break;

                        default:
                            break;

                    }
                });


        return param;

    }

    private String getValueFromRequest(ProxyHttpRequest proxyHttpRequest, SysApiParam sysApiParam) {

        String value = null;

        if (sysApiParam.getPosition().equalsIgnoreCase(ParamPositionEnum.HEADER.name())) {
            value = proxyHttpRequest.getHeaders().get(sysApiParam.getName());
        } else {
            value = proxyHttpRequest.getParams().get(sysApiParam.getName());
        }

        if (value == null) {
            value = sysApiParam.getDefaultValue();
        }

        return value;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(Map<String, String> headerParams) {
        this.headerParams = headerParams;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
