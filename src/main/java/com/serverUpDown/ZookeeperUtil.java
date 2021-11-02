package com.serverUpDown;

/**
 * @author 12604
 */
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ZookeeperUtil {
    public ZooKeeper getZk(String host, int timeOut, Watcher watcher) throws IOException {
       return new ZooKeeper(host, timeOut, watcher);
    }

    public String createNode(ZooKeeper zk, String path, byte[] data) throws InterruptedException, KeeperException {
        return zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public byte[] getData(ZooKeeper zk, String path, Watcher watcher) throws InterruptedException, KeeperException {
        return zk.getData(path, watcher, null);
    }

    public void getServerList(ZooKeeper zk, String path, Watcher watcher) throws InterruptedException, KeeperException {
        List<String> childList = zk.getChildren(path, watcher);
        System.out.println("---------------------------------------------------");
        System.out.println("当前服务器列表" + Arrays.toString(childList.toArray()));
        System.out.println("---------------------------------------------------");
    }

    // 监听节点
    public void addListen2Servers(ZooKeeper zk, String path, Watcher watcher){

    }

}
