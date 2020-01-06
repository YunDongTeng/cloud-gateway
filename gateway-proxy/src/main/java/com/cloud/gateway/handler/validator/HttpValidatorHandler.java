package com.cloud.gateway.handler.validator;

import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.common.context.http.ProxyHttpRequest;
import com.cloud.gateway.common.exception.AppException;
import com.cloud.gateway.entity.SysApi;
import com.cloud.gateway.service.SysApiService;
import com.cloud.gateway.util.ApplicationContextUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

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

        // TODO 校验URI请求、参数校验等信息

        if (StringUtils.isEmpty(uri)) {
            throw new AppException("请求uri为空");
        }

        ctx.fireChannelRead(requestContext);

        ReferenceCountUtil.release(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
