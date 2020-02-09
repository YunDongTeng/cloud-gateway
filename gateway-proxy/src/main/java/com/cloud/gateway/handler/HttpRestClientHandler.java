package com.cloud.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.cloud.gateway.builder.client.ClientRequest;
import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.common.exception.AppException;
import com.cloud.gateway.common.response.HttpClientResult;
import com.cloud.gateway.common.util.HttpClient2Util;
import com.cloud.gateway.common.util.HttpClientUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

/**
 * Http处理
 */
public class HttpRestClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ProxyRequestContext requestContext = (ProxyRequestContext) msg;

        ClientRequest clientRequest = new ClientRequest().build(requestContext.getProxyHttpRequest(), requestContext.getFullApi());

        String result = null;
        if (clientRequest.getMethod().equalsIgnoreCase("GET")) {
            result = HttpClient2Util.get(clientRequest.getBaseUrl() + clientRequest.getUri(), clientRequest.getQueryParams());
        } else if (clientRequest.getMethod().equalsIgnoreCase("POST")) {
            if (clientRequest.getContentType().equalsIgnoreCase("application/json")) {
                result = HttpClient2Util.postForJson(clientRequest.getBaseUrl() + clientRequest.getUri(), clientRequest.getQueryParams());
            } else {
                result = HttpClient2Util.post(clientRequest.getBaseUrl() + clientRequest.getUri(), clientRequest.getQueryParams());
            }
        } else {
            throw new AppException("请求方法错误");
        }

        System.out.println("请求结果：" + result);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.copiedBuffer(result, Charset.defaultCharset()));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        requestContext.setFullHttpResponse(response);

        ctx.writeAndFlush(requestContext);
    }
}
