package DatabaseConn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wypozyczalnia", "root", "");
            return conn;
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
            return null;
        }
    }
}
