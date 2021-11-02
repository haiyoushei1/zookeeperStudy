package com.serverUpDown;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class zkServer {
    public static String host = "172.81.243.159";
    public static final int timeOut = 60000;
    public static void main(String[] str) throws IOException, InterruptedException, KeeperException {
        zkServer server = new zkServer();
        server.nodeRegister();
        server.business();
    }

    public void nodeRegister() throws IOException, InterruptedException, KeeperException {
        ZookeeperUtil zkUtil = new ZookeeperUtil();
        ZooKeeper zk = zkUtil.getZk(host + ":2181", timeOut, new Watcher(){
            @Override
            public void process(WatchedEvent event) {

            }
        });
        String message = "server1";
        String create = zkUtil.createNode(zk, "/servers/server1", message.getBytes(StandardCharsets.UTF_8));
        System.out.println(create + "isOnline");

    }

    public void business() throws InterruptedException {
        System.out.println("server1" + "isWork");
        Thread.sleep(Long.MAX_VALUE);
    }

}
