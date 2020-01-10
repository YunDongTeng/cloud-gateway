package com.cloud.gateway.handler;

import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.common.context.http.ProxyHttpRequest;
import com.cloud.gateway.common.exception.AppException;
import com.cloud.gateway.common.util.ApplicationContextUtil;
import com.cloud.gateway.service.SysFullApiService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class HttpConnectionReceiveHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpConnectionReceiveHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("channel registered {}", ctx.channel().id());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channel active {}", ctx.channel().id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channel inActive {}", ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        boolean httpRequestFlag = msg instanceof HttpRequest;
        if (!httpRequestFlag) {
            logger.info("不是http请求...");

            ReferenceCountUtil.release(msg);
            throw new AppException("非法请求");
        }

        HttpRequest request = (HttpRequest) msg;

        if (request.decoderResult().isFailure()) {
            logger.error("请求解码错误...");
            ReferenceCountUtil.release(msg);

            throw new AppException("请求解码错误");
        }
        logger.info("Method: {}", request.method());
        logger.info("URI: {}", request.uri());
        logger.info("params: {}", request.decoderResult());

        /*if (request.method() == HttpMethod.GET) {
            throw new AppException("请求方法错误");
        }*/
        ProxyRequestContext proxyRequestContext = new ProxyRequestContext();


        ProxyHttpRequest proxyHttpRequest = new ProxyHttpRequest().build(request);
        proxyRequestContext.setProxyHttpRequest(proxyHttpRequest);

        proxyRequestContext.setChannelHandlerContext(ctx);
        proxyRequestContext.setHttpRequest(request);


        ctx.fireChannelRead(proxyRequestContext);

        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
