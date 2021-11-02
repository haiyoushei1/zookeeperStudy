package com.serverUpDown;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class zkClient {
    private ZooKeeper zk = null;
    public void getConnection() throws IOException {
        zk = new ZooKeeper(zkServer.host + ":2182", zkServer.timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getServerList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getServerList() throws InterruptedException, KeeperException {
        List<String> childrenList =  zk.getChildren("/servers", true);
        List<String> nodeList = new ArrayList<>();

        for (String child : childrenList) {
            byte[] data = zk.getData("/servers/" + child, false, null);
            nodeList.add(new String(data));
        }

        for(String node : nodeList){
            System.out.println(node.toString());
        }
    }

    public static void main(String[] str) throws IOException, InterruptedException, KeeperException {

        zkClient zkC = new zkClient();
        zkC.getConnection();
        zkC.getServerList();
        Thread.sleep(Long.MAX_VALUE);

    }
}
