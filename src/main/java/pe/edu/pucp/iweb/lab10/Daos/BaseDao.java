package pe.edu.pucp.iweb.lab10.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://localhost:3306/hr";
        return DriverManager.getConnection(url, user, pass);
    }
}
