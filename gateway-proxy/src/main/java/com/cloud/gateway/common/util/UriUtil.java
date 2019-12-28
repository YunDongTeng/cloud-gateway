package com.cloud.gateway.common.util;

import com.google.common.base.Joiner;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UriUtil {

    /**
     * 获取请求主地址
     *
     * @param hostAddress
     * @return
     */
    public static String getHost(URI hostAddress) {
        return hostAddress.getHost();
    }

    /**
     * 获取端口
     *
     * @param hostAddress
     * @return
     */
    public static Integer getPort(URI hostAddress) {
        int port = hostAddress.getPort();
        if (port > 0) {
            return port;
        }

        //根据请求协议获取默认的端口
        String schema = hostAddress.getScheme();

        if (schema.equalsIgnoreCase("https")) {
            return 443;
        } else if (schema.equalsIgnoreCase("http")) {
            return 80;
        }

        throw new IllegalArgumentException("无法解析请求的端口, " + hostAddress.toString());
    }

    /**
     * 获取请求主地址和端口
     *
     * @param hostAddress
     * @return
     */
    public static String getHostAndPort(URI hostAddress) {
        String host = getHost(hostAddress);
        int port = getPort(hostAddress);

        return host + ":" + port;
    }

    /**
     * 解析请求uri中的参数
     *
     * @param uri
     * @return
     */
    public static Map<String, String> getQueryParams(URI uri) {


        Map<String, String> params = new HashMap<>();

        //解析请求路径中的参数
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri.toString(), Charset.forName("utf-8"));

        Map<String, List<String>> pathParams = queryStringDecoder.parameters();
        if (pathParams != null) {
            pathParams.entrySet().stream().forEach(entry -> {
                params.put(entry.getKey(), entry.getValue() != null ? Joiner.on(",").skipNulls().join(entry.getValue()) : "");
            });
        }

        return params;
    }
}
