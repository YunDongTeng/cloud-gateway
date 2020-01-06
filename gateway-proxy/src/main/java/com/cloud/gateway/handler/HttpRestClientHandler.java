package com.cloud.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.cloud.gateway.builder.client.ClientRequest;
import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.common.response.HttpClientResult;
import com.cloud.gateway.common.util.HttpClientUtil;
import com.cloud.gateway.exception.AppException;
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

        HttpClientResult clientResult;
        if (clientRequest.getMethod().equalsIgnoreCase("GET")) {
            clientResult = HttpClientUtil.doGet(clientRequest.getBaseUrl() + clientRequest.getUri(), clientRequest.getHeaderParams(), clientRequest.getQueryParams());
        } else if (clientRequest.getMethod().equalsIgnoreCase("POST")) {
            clientResult = HttpClientUtil.doPost(clientRequest.getBaseUrl() + clientRequest.getUri(), clientRequest.getHeaderParams(), clientRequest.getQueryParams());
        } else {
            throw new AppException("请求方法错误");
        }

        System.out.println(JSON.toJSON(clientResult));

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.copiedBuffer(JSON.toJSONString(clientResult.getContent()), Charset.defaultCharset()));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        requestContext.setFullHttpResponse(response);

        ctx.writeAndFlush(requestContext);
    }
}
