package com.changda.pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname MyDataSource
 * @description
 * @create 2020-05-03 17:01
 **/
public abstract class MyAbstractDataSource implements DataSource {

    private String url;

    private String driver;

    private String username;

    private String password;

    // 最大的正在使用连接为10
    private int poolMaxActiveConnections = 10;

    // 最大的空闲连接为5
    private int poolMaxIdleConnections = 5;

    // 从连接池中获取一个连接最大等待多少毫秒 3秒
    private int poolTimeToWait = 30000;

    public String getUrl() {
        return url;
    }

    public MyAbstractDataSource setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDriver() {
        return driver;
    }

    public MyAbstractDataSource setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MyAbstractDataSource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MyAbstractDataSource setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getPoolMaxActiveConnections() {
        return poolMaxActiveConnections;
    }

    public MyAbstractDataSource setPoolMaxActiveConnections(int poolMaxActiveConnections) {
        this.poolMaxActiveConnections = poolMaxActiveConnections;
        return this;
    }

    public int getPoolMaxIdleConnections() {
        return poolMaxIdleConnections;
    }

    public MyAbstractDataSource setPoolMaxIdleConnections(int poolMaxIdleConnections) {
        this.poolMaxIdleConnections = poolMaxIdleConnections;
        return this;
    }

    public int getPoolTimeToWait() {
        return poolTimeToWait;
    }

    public MyAbstractDataSource setPoolTimeToWait(int poolTimeToWait) {
        this.poolTimeToWait = poolTimeToWait;
        return this;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(username, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return doGetConnection(username, password);
    }

    /**
     * 获取数据库连接
     * @param username
     * @param password
     * @return
     */
    private Connection doGetConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 以下可以不重写
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
