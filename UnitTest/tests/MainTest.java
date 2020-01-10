import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;



public class  MainTest {


    @Test
    public void testLogInSys() throws InterruptedException, SQLException {
        Main main = new Main();
        main.SetLogInSys();
        assertEquals(2,main.logInSys.logIn("admin","0000"));
        assertEquals(1,main.logInSys.logIn("Howard","0324"));
        assertEquals(-1,main.logInSys.logIn("admin","1111"));
        assertEquals(-1,main.logInSys.logIn("adm","0000"));
        assertEquals(-1,main.logInSys.logIn("\" or \"\"=\"","\" or \"\"=\""));
        assertEquals(-1,main.logInSys.logIn("\" or \"\"=\"","0000"));
        assertEquals(-1,main.logInSys.logIn("admin","\" or \"\"=\""));
        assertEquals(-1,main.logInSys.logIn("\"\";drop table user","000"));
    }

}
