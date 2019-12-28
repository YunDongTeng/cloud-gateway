package com.cloud.gateway.bootstrap;

import com.cloud.gateway.common.ProxyProperty;
import com.cloud.gateway.initializer.ChannelHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Netty服务端启动类（代理端启动类）
 */
public class ProxyServerBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(ProxyServerBootstrap.class);

    public void start() {

        // SocketAddress socketAddress = new InetSocketAddress(proxyProperty.getPort());

        // 创建一个主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建一个工作线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup(200);

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelHandlerInitializer())
                .localAddress(8090)
                // 设置队列大小
                .option(ChannelOption.SO_BACKLOG, 2014)
                // 两小时没有数据通信，TCP会自动发送一条数据探测报文
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        // 绑定端口，开始接收连接
        try {
            ChannelFuture channelFuture = bootstrap.bind(8090).sync();
            logger.info("Netty服务器启动监听端口：{}", 8090);

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭工作线程组
            workerGroup.shutdownGracefully();

            // 关闭主线程组
            bossGroup.shutdownGracefully();
        }

    }

}
