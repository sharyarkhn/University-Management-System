package ProjectWithSherry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;

    public Connection getConnection() { return connection; }
    public Statement getStatement()   { return statement; }

    // Helper to create PreparedStatements easily
    public PreparedStatement prepare(String sql) throws Exception {
        return connection.prepareStatement(sql);
    }

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql:///universityManagementSystem", "sherry", "sherry321");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}