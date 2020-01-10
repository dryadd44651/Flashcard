import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LogInUI extends uiController{
    //obj for login

    JPanel panel = new JPanel(); // the panel is not visible in output
    GridBagLayout loginLayout = new GridBagLayout();
    JLabel accLabel = new JLabel("Account:");
    JLabel pawLabel = new JLabel("Password:");
    JTextField tfAcc = new JTextField(10); // accepts upto 10 characters
    JTextField tfPaw = new JPasswordField(10); // accepts upto 10 characters
    JButton logInBtn = new JButton("Log In Flash Card");
    JButton logInEditBtn = new JButton("Log In Editor");
    JButton signUpBtn = new JButton("Sign Up");
    JButton cancelBtn = new JButton("Cancel");
    public User getUser(){
        User user = new User(tfAcc.getText(), tfPaw.getText());
        return user;
    }
    LogInUI(int id,String title){
        super(id,title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);


        //login obj
        panel.setLayout(loginLayout);
        panel.add(accLabel);
        panel.add(tfAcc);
        panel.add(logInBtn);
        panel.add(logInEditBtn);

        panel.add(pawLabel);
        panel.add(tfPaw);
        panel.add(signUpBtn);
        panel.add(cancelBtn);

        //login layout
        GridBagConstraints setting = new GridBagConstraints();

        loginLayout.setConstraints(logInBtn, setting);
//        setting.fill = GridBagConstraints.NONE;
//        setting.weightx = 0;//width extend
//        setting.weighty = 0;//height don't extend
        setting.gridwidth = 0;//width size 0: change row
        loginLayout.setConstraints(logInEditBtn, setting);

        //frame add
        this.getContentPane().add(BorderLayout.CENTER, panel);

    }


    void addLogInCardBtn(ActionListener listener){
        logInBtn.addActionListener(listener);
    }
    void addLogInEditBtn(ActionListener listener){
        logInEditBtn.addActionListener(listener);
    }
    void addSignUpBtn(ActionListener listener){
        signUpBtn.addActionListener(listener);
    }
    void addCancelBtn(ActionListener listener){
        cancelBtn.addActionListener(listener);
    }


}
