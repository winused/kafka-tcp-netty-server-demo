package io.conduktor.demos.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class SimpleNettyTCPHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Handle incoming messages here

        ByteBuf byteBuf;
        String data, hostAddress;

        byteBuf = (ByteBuf) msg;
        data = byteBuf.toString(CharsetUtil.UTF_8);
        hostAddress = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();

        if (!data.isEmpty()) {
            String reply;

            System.out.println("Data received " + data + " from " + hostAddress);
            reply = "Clienta Cevap Gonder";

            if (reply != null) {
                ctx.writeAndFlush(Unpooled.copiedBuffer(reply, CharsetUtil.UTF_8));
                System.out.println("Clienta cevap goncerildi");
            }
        }
        else {
            System.out.println("NO Data received from " + hostAddress);
        }



        /********/
        //System.out.println("Received message: " + );
        //String message = ((ByteBuf) msg).toString();
        //System.out.println("Received message: " + message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Handle exceptions here
        cause.printStackTrace();
        ctx.close();
    }
}

