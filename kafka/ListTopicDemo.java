package io.conduktor.demos.kafka;

import org.apache.zookeeper.ZooKeeper;
import java.util.List;


public class ListTopicDemo {

    public static void main(String[] args) throws Exception{

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 10000, null);
        List<String> topics = zk.getChildren("/brokers/topics", false);

        System.out.println(topics);

    }
}
