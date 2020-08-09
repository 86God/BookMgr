package com.company.test;

import com.company.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilsTest {
    @Test
    public void get() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);

        connection.close();
    }
}
