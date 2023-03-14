import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    Statement s;
    Connection c;
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:MySQL://localhost:3306/bank", "root", "123456");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}