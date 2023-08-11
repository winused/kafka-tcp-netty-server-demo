package io.conduktor.demos.tcp;

public class Main {

    public static void main(String[] args) {
        try {
            SimpleNettyServerBootstrap simpleNettyServerBootstrap = new SimpleNettyServerBootstrap();
            simpleNettyServerBootstrap.start(8888);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}