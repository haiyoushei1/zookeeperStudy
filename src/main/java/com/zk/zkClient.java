package com.zk;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class zkClient {
    private static String host = "172.81.243.159";
    private static String connectString1 = host + ":2181";
    private static String connectString2 = host + ":2182";
    private static String connectString3 = host + ":2183";
    private static int sessionTimeout = 60000;

    private ZooKeeper zkCli1;
    private ZooKeeper zkCli2;
    private ZooKeeper zkCli3;
    @Before
    public void init() throws IOException {

         zkCli1 = new ZooKeeper(connectString1, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        zkCli2 = new ZooKeeper(connectString1, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        zkCli3 = new ZooKeeper(connectString1, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    @Test
    public void create() throws IOException, InterruptedException, KeeperException {
        init();
        String nodeData = zkCli1.create("/zkTest1", "test_one".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


    }
}
