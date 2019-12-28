package com.cloud.gateway.initializer;

import com.cloud.gateway.handler.HttpConnectionReceiveHandler;
import com.cloud.gateway.handler.HttpFinalReadHandler;
import com.cloud.gateway.handler.HttpFinalWriteHandler;
import com.cloud.gateway.handler.validator.HttpValidatorHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;


public class ChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline();
        // 添加Http请求解码器
        channelPipeline.addLast("codec", new HttpServerCodec());

       /* channelPipeline.addLast("decoder", new StringDecoder());
        channelPipeline.addLast("encoder", new StringEncoder());*/

        channelPipeline.addLast(new HttpObjectAggregator(8192));

        channelPipeline.addLast(new HttpConnectionReceiveHandler());

        channelPipeline.addLast(new HttpFinalWriteHandler());

        channelPipeline.addLast(new HttpValidatorHandler());

        channelPipeline.addLast(new HttpFinalReadHandler());

    }
}
