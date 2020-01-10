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
		dbm.addCard("����","Question");
		dbm.addCard("���","Hello");
		dbm.addCard("\"\\\"","0000");
	}
	
	public void deleteCardTest() throws SQLException {
		DBM dbm = new DBM();
		dbm.logIn("admin","0000");
		dbm.deleteCard("Gracias");
		dbm.deleteCard("����");
		dbm.deleteCard("���");
		dbm.deleteCard("\"\\\"");
	}
	
	public void setCardTest() throws SQLException {
		DBM dbm = new DBM();
		dbm.logIn("admin","0000");
		dbm.setCard("Gracias","лл");
		dbm.setCard("����","���}");
		dbm.setCard("���","Hola");
	}
}
