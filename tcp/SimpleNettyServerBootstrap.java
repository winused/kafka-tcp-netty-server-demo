package io.conduktor.demos.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

class SimpleNettyServerBootstrap {

    void start(int port) throws InterruptedException {
        Utils.log("Starting server at: " + port);
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();// (2)
        try {
            ServerBootstrap b = new ServerBootstrap();// (3)
            b.group(bossGroup, workerGroup) // (4)
                    .channel(NioServerSocketChannel.class)// (5)
                    .childHandler(new SimpleTCPChannelInitializer())// (6)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);// (7)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();// (8)
            if(f.isSuccess()) Utils.log("Server started successfully"); // (9)
            f.channel().closeFuture().sync(); // (10)
        } finally {
            Utils.log("Stopping server");
            workerGroup.shutdownGracefully();// (11)
            bossGroup.shutdownGracefully();// (12)
        }
    }

}