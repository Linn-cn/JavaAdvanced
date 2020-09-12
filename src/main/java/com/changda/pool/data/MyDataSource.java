package com.changda.pool.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname MyDataSource
 * @description 数据源的连接池
 * @create 2020-05-03 17:23
 **/
public class MyDataSource extends MyAbstractDataSource {
    // 空闲连接池
    private final List<ConnectionProxy> idleConnections = new ArrayList<>();

    // 激活的连接池
    private final List<ConnectionProxy> activeConnections = new ArrayList<>();

    // 监视器对象
    private final Object monitor = new Object();
    private final Object watch = new Object();

    /**
     * 覆盖父类的获取连接方法，返回的是代理对象
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        ConnectionProxy proxy = getConnectionProxy(super.getUsername(), super.getPassword());
        return proxy.getProxyConnection();
    }

    public ConnectionProxy getConnectionProxy(String username, String password) throws SQLException {
        boolean wait = false;
        ConnectionProxy connectionProxy = null;
        while (connectionProxy == null) {
            // 同步操作
            synchronized (monitor) {
                // 判断空闲连接池是不是空的，不是空的就可以直接获取
                if (!idleConnections.isEmpty()) {
                    connectionProxy = idleConnections.remove(0);
                } else {
                    // 获取新的连接
                    if (activeConnections.size() < super.getPoolMaxActiveConnections()) {
                        connectionProxy = new ConnectionProxy(super.getConnection(), this);
                    }
                    // 否则需要等待

                }

                if (!wait) {
                    wait = true;
                }

                if (connectionProxy == null) {
                    try {
                        // 连接对象是空，需要等待
                        monitor.wait(super.getPoolTimeToWait());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 循环终止
                        break;
                    }
                }
            }
        }
        if (connectionProxy != null) {
            activeConnections.add(connectionProxy);
        }
        return connectionProxy;
    }

    /**
     * 关闭连接
     * @param connectionProxy
     */
    public void closeConnection(ConnectionProxy connectionProxy) {
        synchronized (monitor) {
            activeConnections.remove(connectionProxy);
            if (idleConnections.size() < super.getPoolMaxIdleConnections()) {
                idleConnections.add(connectionProxy);
            }
            // 通知一下上面等待获取连接的线程
            monitor.notifyAll();
        }
    }
}
