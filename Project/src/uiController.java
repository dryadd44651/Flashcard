import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public abstract class uiController extends JFrame {//controller
    int id;
    boolean active = true;
    protected uiController nextUI;

    uiController(int id,String title){
        super(title);
        this.id = id;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        //=====
        setLocationRelativeTo(null);
        //setLocation(dx, dy);


        //====
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void SetNextUI(uiController nextUI) {
        this.nextUI = nextUI;
    }
    public synchronized void switcher(int token,Point p) {
        setLocation(p);
        if(token == id){
            this.setVisible(true);
        }else{
            nextUI.switcher(token,getLocation());
            this.setVisible(false);
        }
    }
    public void closeAll() {
        active = false;
        if (nextUI.active==true)
            nextUI.closeAll();
        this.dispose();
    }
    //abstract void RunUI() throws SQLException, UnsupportedEncodingException;
    //abstract void setVisible(boolean t);
}
