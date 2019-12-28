package com.cloud.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.exception.exception.AppException;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;
import java.util.Map;

@ChannelHandler.Sharable
public class HttpFinalReadHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        FullHttpResponse fullHttpResponse = null;
        HttpResponse response;
        if (cause instanceof AppException) {

            AppException appException = (AppException) cause;

            String message = appException.getMessage();

            Map<String,String> errorData = Maps.newHashMap();
            errorData.put("message",message);
            errorData.put("success","false");

            String data = JSON.toJSONString(errorData);

            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_REQUEST,Unpooled.wrappedBuffer(data.getBytes()));
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
            fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.writeAndFlush(fullHttpResponse);

    }
}
