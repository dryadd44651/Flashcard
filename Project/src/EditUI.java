import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class EditUI  extends uiController{
    //private JFrame frame = new JFrame("FlashCard");
    private JLabel frontLabel = new JLabel("Front:");
    private JLabel backLabel = new JLabel("Back:");

    public String getFrontTf() {
        return frontTf.getText();
    }
    String  getBackTf(){return backTf.getText();}
    private JTextField frontTf = new JTextField(10); // accepts upto 10 characters
    private JTextField backTf = new JTextField(10); // accepts upto 10 characters
    private JButton updateBtn = new JButton("Update");
    private JButton deleteBtn = new JButton("Delete");
    private JButton viewBtn = new JButton("View");
    private JButton logOutBtn = new JButton("Log Out");
    private JButton BatchAddBtn = new JButton("Batch Add");
    private JPanel panel = new JPanel();
    private GridBagLayout Layout = new GridBagLayout();


    EditUI(int id,String title){
        super(id,title);
        panel.setLayout(Layout);
        panel.add(frontLabel);
        panel.add(frontTf);
        panel.add(backLabel);
        panel.add(backTf);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(viewBtn);
        panel.add(logOutBtn);
        panel.add(BatchAddBtn);

        GridBagConstraints setting = new GridBagConstraints();

        //setting.gridwidth = 0;//new line
        Layout.setConstraints(frontLabel, setting);
        setting.gridwidth = 0;
        Layout.setConstraints(frontTf, setting);
        setting.gridwidth = 1;
        Layout.setConstraints(backLabel, setting);
        setting.gridwidth = 0;
        Layout.setConstraints(backTf, setting);
        setting.gridwidth = 1;
        Layout.setConstraints(updateBtn, setting);
        Layout.setConstraints(deleteBtn, setting);
        Layout.setConstraints(viewBtn, setting);
        setting.gridwidth = 0;
        Layout.setConstraints(BatchAddBtn, setting);
        Layout.setConstraints(logOutBtn, setting);
        this.getContentPane().add(BorderLayout.CENTER, panel);

    }
    void addUpdateBtn(ActionListener listener){
        updateBtn.addActionListener(listener);
    }
    void addDeleteBtn(ActionListener listener){
        deleteBtn.addActionListener(listener);
    }
    void addViewBtn(ActionListener listener){
        viewBtn.addActionListener(listener);
    }
    void addLogOutBtn(ActionListener listener){
        logOutBtn.addActionListener(listener);
    }
    void addBatchAddBtn(ActionListener listener){
        BatchAddBtn.addActionListener(listener);
    }
}
