package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB", "Scooby1468", "filipokSS1968");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }

    public static void shutdownConnect() throws SQLException {
        connection.close();
    }
}
