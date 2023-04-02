package com.learn.lcf.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 1 创建两个NioEventGroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup bizGroup = new NioEventLoopGroup();

        // 2 创建一个serverBootStrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 3 设置bootstrap的参数；两个group，channel的类型，等待连接的线程数量，是否keepalive
        serverBootstrap.group(bossGroup, bizGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /**
                     * This method will be called once the {@link Channel} was registered. After the method returns this instance
                     * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
                     *
                     * @param ch the {@link Channel} which was registered.
                     * @throws Exception is thrown if an error occurs. In that case it will be handled by
                     *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
                     *                   the {@link Channel}.
                     */
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandle());
                    }
                });

        // 4 bootstrap.bind 启动一个服务
        ChannelFuture chl = serverBootstrap.bind(8888).sync();

        chl.channel().closeFuture().sync();


    }
}
