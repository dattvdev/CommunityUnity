package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TWNA21
 */
public class DBUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Volunteer;characterEncoding=UTF-8";
        conn = DriverManager.getConnection(url, "sa", "123asd");
        return conn;
    }

}