package io.conduktor.demos.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleNettyTCPServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // Handles incoming connections
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // Handles the traffic of accepted connections

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            System.out.println(this.getClass().getSimpleName() + " initChannel");
                            ch.pipeline().addLast(new SimpleNettyTCPHandler()); // Replace this with your custom handler
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            Channel serverChannel = bootstrap.bind(11111).sync().channel(); // Replace 8888 with your desired port

            System.out.println("SimpleNettyTCPServer: Server started and listening on port 11111.");

            serverChannel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
