package io.conduktor.demos.tcp;



import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleTCPChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Utils.log(ctx.channel().remoteAddress(), "Channel Active");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        Utils.log(ctx.channel().remoteAddress(), s);
        byte[] test = new byte[]{ 0x15, 0x16, 0x17};

        /*ByteBuf buf= ByteBufUtil.writeAscii(AbstractByteBufAllocator.DEFAULT,"0D");*/
        ctx.channel().writeAndFlush(test);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Utils.log(ctx.channel().remoteAddress(), "Channel Inactive");
    }
}