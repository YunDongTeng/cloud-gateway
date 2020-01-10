package com.cloud.gateway.handler.validator;

import com.cloud.gateway.common.context.ProxyRequestContext;
import com.cloud.gateway.common.context.http.ProxyHttpRequest;
import com.cloud.gateway.common.exception.AppException;
import com.cloud.gateway.common.util.ApplicationContextUtil;
import com.cloud.gateway.service.SysFullApiService;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.springframework.util.StringUtils;

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


        SysFullApiService sysFullApiService = ApplicationContextUtil.getBean(SysFullApiService.class);

        requestContext.setFullApi(sysFullApiService.loadFullApi(uri));

        ctx.fireChannelRead(requestContext);

        ReferenceCountUtil.release(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
