package com.cloud.gateway.handler.validator;

import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.common.context.http.ProxyHttpRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.Map;

@ChannelHandler.Sharable
public class HttpValidatorHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ProxyRequestContext requestContext = (ProxyRequestContext) msg;
        ProxyHttpRequest proxyHttpRequest = requestContext.getProxyHttpRequest();

        String uri = proxyHttpRequest.getUri();
        Map<String, String> params = proxyHttpRequest.getParams();

        String result = new RestTemplate().getForObject("http://192.168.103.195:8080/api/cloud/org/root?source=area", String.class, params);

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.copiedBuffer(result, Charset.defaultCharset()));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        requestContext.setFullHttpResponse(response);

        ctx.writeAndFlush(requestContext);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
