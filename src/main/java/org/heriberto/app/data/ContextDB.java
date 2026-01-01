package org.heriberto.app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContextDB {
    private static final String driver = "jdbc:mysql://localhost:3306/java_connection?serverTimezone=America/Tijuana";
    private static final String root = "root";
    private static final String password = "klok123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(driver, root, password);
    }

}
