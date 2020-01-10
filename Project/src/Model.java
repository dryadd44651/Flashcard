import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Model extends DBM{
    Scheduler scheduler = null;

    Model(String DB ,String user,String pass){
        super(DB,user,pass);


    }

    Card getNextCart(String mode, String front) throws SQLException {
        int number = 1,index = 0;
        ArrayList<Integer> list = getAllCard();
        String sql ="SELECT * from "+account+" where front = \""+front+"\"";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        index = rs.getInt("id");
        switch (mode){
            case "order":
                scheduler = new orderSchedule();
                break;
            case "random":
                scheduler = new randomSchedule();
                break;
            case "review":
                scheduler = new reviewSchedule();
                break;
        }
        number = scheduler.getNextCardNum(list, index);
        System.out.println(mode);
        return getCard(number);
    }
}
