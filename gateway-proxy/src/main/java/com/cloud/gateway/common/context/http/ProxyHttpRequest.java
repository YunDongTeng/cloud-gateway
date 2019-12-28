package com.cloud.gateway.common.context.http;

import com.cloud.gateway.common.util.UriUtil;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

public class ProxyHttpRequest {

    private String uri;

    private HttpMethod method;

    private HttpHeaders headers;

    private Cookie[] cookies;

    private String appCode;

    private Map<String, String> params;


    public ProxyHttpRequest build(HttpRequest request) throws URISyntaxException {

        ProxyHttpRequest proxyHttpRequest = new ProxyHttpRequest();

        // 组装cookies
        String cookieHeader = request.headers().get(HttpHeaderNames.COOKIE);
        if (cookieHeader != null) {
            Set<Cookie> cookieSet = ServerCookieDecoder.LAX.decode(cookieHeader);
            proxyHttpRequest.cookies = cookieSet.toArray(new Cookie[]{});
        }


        // 组装请求参数
        // 注意：请求参数分为三种类型： 1-uri路径拼接的参数 ?name=zhangsan&age=20 2-form表单参数 3-json格式参数
        Map<String, String> uriParams = UriUtil.getQueryParams(new URI(request.uri()));
        proxyHttpRequest.params = uriParams;

        proxyHttpRequest.uri = request.uri();

        proxyHttpRequest.method = request.method();

        proxyHttpRequest.headers = request.headers();

        return proxyHttpRequest;

    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
