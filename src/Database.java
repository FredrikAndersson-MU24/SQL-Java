import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/shop_db";
    private static final String user = System.getenv("SQL_USERNAME");
    private static final String password = System.getenv("SQL_PASSWORD");

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

}