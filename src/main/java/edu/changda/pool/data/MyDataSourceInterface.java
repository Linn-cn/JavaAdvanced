package edu.changda.pool.data;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname MyDataSourceInterface
 * @description
 * @create 2020-05-03 16:56
 **/
public interface MyDataSourceInterface extends DataSource {

    @Override
    default Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    default Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    // 以下方法可以不实现

    @Override
    default <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    default boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    default PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    default void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    default void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    default int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    default Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
