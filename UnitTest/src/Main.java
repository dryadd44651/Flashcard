import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    Connection conn = null;
    Statement stmt = null;
    Main(){
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=yes&characterEncoding=UTF-8";
        final String USER = "root";
        final String PASS = "0324";

        Connection cardConn = null;
        Statement cardStmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    LogInSys logInSys;
    void SetLogInSys(){
        logInSys = new LogInSys(conn,stmt);
    }
    public static void main(String[] args) {


    }
}
