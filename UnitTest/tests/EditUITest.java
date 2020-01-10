import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class EditUITest {
    
	@Test
	public void viewCardTest() throws SQLException {
		DBM dbm = new DBM();
		dbm.logIn("admin","0000");
		dbm.viewCard();
	}
	
	public void addCardTest() throws SQLException {
		DBM dbm = new DBM();
		dbm.logIn("admin","0000");
		dbm.addCard("Gracias","Thanks");
		dbm.addCard("问题","Question");
		dbm.addCard("你好","Hello");
		dbm.addCard("\"\\\"","0000");
	}
	
	public void deleteCardTest() throws SQLException {
		DBM dbm = new DBM();
		dbm.logIn("admin","0000");
		dbm.deleteCard("Gracias");
		dbm.deleteCard("问题");
		dbm.deleteCard("你好");
		dbm.deleteCard("\"\\\"");
	}
	
	public void setCardTest() throws SQLException {
		DBM dbm = new DBM();
		dbm.logIn("admin","0000");
		dbm.setCard("Gracias","谢谢");
		dbm.setCard("问题","問題");
		dbm.setCard("你好","Hola");
	}
}
