import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInSys {
    private int priorit = 0;// 0: guest, 1: user, 2: admin
    Connection conn = null;
    Statement stmt = null;
    LogInSys(Connection conn,Statement stmt){
        this.conn = conn;
        this.stmt = stmt;
    }
    void signUp(String acc,String paw) throws SQLException {
        String sql ="SELECT * from user where account = \""+acc+"\"";;
        ResultSet rs = null;
        try {//check the acc is exit or not
            rs = stmt.executeQuery(sql);
            rs.next();//get next record
            if(rs.getString("account").compareTo(acc)==0);
            System.out.println("account exit");
        } catch (SQLException e) {
            sql = "INSERT INTO user VALUES (\""+acc+"\", \""+paw+"\", 1);";
            stmt.executeUpdate(sql);
            System.out.println("Sign up!");
        }
    }
    void initial() throws SQLException {
        String sql = "CREATE TABLE user( account varchar(255), password varchar(255), priority int);";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO user VALUES (\"admin\", \"0000\", 2);";
        stmt.executeUpdate(sql);
    }
    int logIn(String acc,String paw) throws SQLException {
        acc = acc.replace("\"","");
        paw = paw.replace("\"","");
        String sql;
        sql = "SELECT * from user where account = \""+acc+"\"";
        ResultSet rs = null;
        String userPaw = null;
        int userPriority = -1;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();//get next record

        } catch (SQLException e) {
            initial();
            rs = stmt.executeQuery(sql);
            rs.next();//get next record
        }
        try {
            userPaw = rs.getString("password");
            userPriority = rs.getInt("priority");
        } catch (SQLException e) {
            System.out.println("The Account doesn't exit");
            return -1;
        }

        if(paw.compareTo(userPaw)==0) {
            System.out.println("Log in!");
            return userPriority;
        }
        else {
            System.out.println("Wrong Password");
            return -1;//wrong password/account
        }
    }
}//loginsys
