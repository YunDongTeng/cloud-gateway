package com.cloud.gateway.common.context;

import com.cloud.gateway.common.context.http.ProxyHttpRequest;
import com.cloud.gateway.entity.SysFullApi;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

public class ProxyRequestContext {

    // ChannelHandler 上下文
    private ChannelHandlerContext channelHandlerContext;

    // 代理请求对象
    private ProxyHttpRequest proxyHttpRequest;

    // 代理响应对象
    private FullHttpResponse fullHttpResponse;

    // 网关信息
    private SysFullApi fullApi;

    // 客户端真实Http请求
    private HttpRequest httpRequest;

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    public ProxyHttpRequest getProxyHttpRequest() {
        return proxyHttpRequest;
    }

    public void setProxyHttpRequest(ProxyHttpRequest proxyHttpRequest) {
        this.proxyHttpRequest = proxyHttpRequest;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public FullHttpResponse getFullHttpResponse() {
        return fullHttpResponse;
    }

    public void setFullHttpResponse(FullHttpResponse fullHttpResponse) {
        this.fullHttpResponse = fullHttpResponse;
    }

    public SysFullApi getFullApi() {
        return fullApi;
    }

    public void setFullApi(SysFullApi fullApi) {
        this.fullApi = fullApi;
    }
}
