package edu.changda.pool.data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname ConnectionProxy
 * @description
 * @create 2020-05-03 17:09
 **/
public class ConnectionProxy implements InvocationHandler {

    // 真实连接
    private Connection realConnection;

    // 代理连接
    private Connection proxyConnection;

    // 数据源引用
    private MyDataSource dataSource;

    public Connection getRealConnection() {
        return realConnection;
    }

    public ConnectionProxy setRealConnection(Connection realConnection) {
        this.realConnection = realConnection;
        return this;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public ConnectionProxy setProxyConnection(Connection proxyConnection) {
        this.proxyConnection = proxyConnection;
        return this;
    }

    public MyAbstractDataSource getDataSource() {
        return dataSource;
    }

    public ConnectionProxy setDataSource(MyDataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public ConnectionProxy(Connection realConnection, MyDataSource dataSource) {
        // 初始化真实连接
        this.realConnection = realConnection;
        // 初始化连接源
        this.dataSource = dataSource;
        // 初始化代理连接
        this.proxyConnection = (Connection) Proxy.newProxyInstance(realConnection.getClass().getClassLoader(),
                new Class[]{Connection.class},
                this
        );
    }

    /**
     * 当调用Connection对象的时候，会被这个invoke方法拦截
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取到当前的方法名
        String methodName = method.getName();
        if ("close".equalsIgnoreCase(methodName)) {
            // 把连接归还到连接池
            dataSource.closeConnection(this);
            return null;
        } else {
            return method.invoke(realConnection, args);
        }
    }
}
