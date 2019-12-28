package com.cloud.gateway.handler;

import com.cloud.gateway.common.context.ProxyRequestContext;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.ReferenceCountUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Proxy;
import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class HttpFinalWriteHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        FullHttpResponse fullHttpResponse = null;

        if (msg instanceof FullHttpResponse) {
            fullHttpResponse = (FullHttpResponse) msg;
        } else if (msg instanceof ProxyRequestContext) {
            ProxyRequestContext proxyRequestContext = (ProxyRequestContext) msg;
            fullHttpResponse = proxyRequestContext.getFullHttpResponse();
        }
        String result = new String(ByteBufUtil.getBytes(fullHttpResponse.content()), Charset.forName("UTF-8"));


        fullHttpResponse = fullHttpResponse.replace(Unpooled.wrappedBuffer(result.getBytes(Charset.forName("UTF-8"))));

        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());

        ctx.writeAndFlush(fullHttpResponse, promise);

        ReferenceCountUtil.release(msg);


    }
}
