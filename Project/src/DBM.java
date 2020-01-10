import java.sql.*;
import java.util.ArrayList;

public class DBM {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=yes&characterEncoding=UTF-8";
    String USER = "root";
    String PASS = "0324";
    Connection conn = null;
    Statement stmt = null;
    Connection cardConn = null;
    Statement cardStmt = null;
    String account = "";


    DBM(String DB ,String user,String pass){
        USER = user;
        PASS = pass;
        DB_URL = "jdbc:mysql://localhost:3306/"+DB+"?useUnicode=yes&characterEncoding=UTF-8";
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            cardConn = DriverManager.getConnection(DB_URL, USER, PASS);
            cardStmt = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
    }
    int execute(String cmd) {
        //String insertSql = "INSERT INTO "+account+" (front,back) VALUES (\""+front+"\", \""+back+"\");";
        int matched = 0;
        try {
            matched = stmt.executeUpdate(cmd);
        } catch (SQLException e) {
            System.out.println("error/duplicated data");
        }
        return matched;
    }
    void addCard(Card card) throws SQLException {
        String front = card.getFront();
        String back = card.getBack();
        String sql = "insert into "+account+" (front,back) values (\""+ front +"\",\""+ back +"\");";
        ResultSet rs = null;
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("card exit");
        }
    }
    Card getCard(int id) throws SQLException {
        String sql;
        sql = "SELECT * from "+account+" where id = \""+id+"\"";
        ResultSet rs = null;
        String front = null,back = null;
        Card card = new Card("","");
        int userPriority = -1;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();//get next record

        } catch (SQLException e) {
            iniCard();
            rs = stmt.executeQuery(sql);
            rs.next();//get next record
        }
        try {
            //rs.getString("front")
            card.setFront(rs.getString("front"));
            //card.add((new String(rs.getString("front").getBytes("UTF-8"))));//(new String(card.get(0).getBytes("UTF-8")))
            card.setBack(rs.getString("back"));
        } catch (SQLException  e) {
            System.out.println("The card doesn't exit");
        }
        return card;

    }
    void setCard(String front,String back) throws SQLException {

        String updateSql = "UPDATE "+account+" set back = \""+back+"\" where front = \""+front+"\"";
        //String checkSql = "SELECT * from "+account+" where front = \""+front+"\"";
        String insertSql = "INSERT INTO "+account+" (front,back) VALUES (\""+front+"\", \""+back+"\");";

        ResultSet rs = null;
        int matched = 0;
        try {
            //rs = stmt.executeQuery(checkSql);
            matched = stmt.executeUpdate(updateSql);
            if (matched==0)
                matched = stmt.executeUpdate(insertSql);
            System.out.println(matched);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    void deleteCard(String front){
        String deleteSql = "delete from "+account+" where front = \""+front+"\"";


        ResultSet rs = null;
        int matched = 0;
        try {
            //rs = stmt.executeQuery(checkSql);
            matched = stmt.executeUpdate(deleteSql);
            if (matched==0)
                System.out.println("no such card");
            System.out.println(matched);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    void viewCard(){
        String sql ="SELECT * from "+account+"";
        ResultSet rs = null;
        try {//check the acc is exit or not
            rs = stmt.executeQuery(sql);
            while (rs.next()){

                System.out.print("Front: "+rs.getString("front"));
                System.out.print(" Back: "+rs.getString("back"));
                System.out.println(" Score: "+rs.getString("score"));

            }


        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    ArrayList<Integer> getAllCard(){
        String sql ="SELECT * from "+account+"";
        ResultSet rs = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {//check the acc is exit or not
            rs = stmt.executeQuery(sql);
            while (rs.next()){

                //System.out.print("Front: "+rs.getString("front"));
                //System.out.print(" Back: "+rs.getString("back"));
                //System.out.println(" Score: "+rs.getString("score"));
                list.add(Integer.valueOf(rs.getString("score")));
                
            }


        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    void iniCard() throws SQLException {
        String sql = "CREATE TABLE "+account+"( id int NOT NULL AUTO_INCREMENT PRIMARY KEY,front varchar(255) UNIQUE, back varchar(255), score int DEFAULT 0) DEFAULT CHARSET=utf8;";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO "+account+" (front,back) VALUES (\"你好\", \"Hello\");";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "INSERT INTO "+account+" (front,back) VALUES (\"問題\", \"Question\");";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
    void submitCard(String front,int score) throws SQLException {
        String updateSql = "UPDATE "+account+" set score = \""+score+"\" where front = \""+front+"\"";
        int matched = 0;
        matched = stmt.executeUpdate(updateSql);
        System.out.println(matched);

    }

    void addUser(User user) throws SQLException {
        String acc = user.getAccount();
        String paw = user.getPassword();
        String sql ="SELECT * from user where account = \""+acc+"\"";;
        this.account = acc;
        ResultSet rs = null;
        try {//check the acc is exit or not
            rs = stmt.executeQuery(sql);
            rs.next();//get next record
            if(rs.getString("account").compareTo(acc)==0);
            System.out.println("account exit");
        } catch (SQLException e) {
            sql = "INSERT INTO user VALUES (\""+acc+"\", \""+paw+"\", 1);";
            stmt.executeUpdate(sql);
            iniCard();
            System.out.println("Sign up!");
        }
    }
    void iniLogIn() throws SQLException {
        String sql = "CREATE TABLE user( account varchar(255), password varchar(255), priority int);";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO user VALUES (\"admin\", \"0000\", 2);";
        stmt.executeUpdate(sql);
        iniCard();
    }

    User searchUser(String acc) throws SQLException {
        acc = acc.replace("\"","");
        //paw = paw.replace("\"","");
        this.account = acc;
        String sql;
        sql = "SELECT * from user where account = \""+acc+"\"";
        ResultSet rs = null;
        String paw = null;
        try {
            rs = stmt.executeQuery(sql);
            rs.next();//get next record

        } catch (SQLException e) {
            iniLogIn();
            rs = stmt.executeQuery(sql);
            rs.next();//get next record
        }
        try {
            paw = rs.getString("password");
            return new User(acc,paw);
            //id = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("The Account doesn't exit");
            return null;
        }


    }


}
