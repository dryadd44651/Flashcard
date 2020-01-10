import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Controller {

    Model model;
    LogInUI logInUI = new LogInUI(0,"Flash Card Log In");
    CardUI cardUI = new CardUI(1,"Playing Flash Card");
    EditUI editUI = new EditUI(2,"Add/Drop Flash Card");
    //DBM dbm = new DBM();
    public Controller() throws UnsupportedEncodingException {
        String dbName,user,pass;
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("config.ini"));
            dbName =  p.getProperty("dbName");
            user =  p.getProperty("user");
            pass =  p.getProperty("pass");

        } catch (IOException e) {
            dbName =  "mydatabase";
            user =  "root";
            pass =  "0324";
        }
        model = new Model(dbName,user,pass);
    }

    void run(){
        logInUI.setVisible(true);
        logInUI.SetNextUI(cardUI);
        cardUI.SetNextUI(editUI);
        editUI.SetNextUI(logInUI);
        //log in
        logInUI.addLogInCardBtn(new logInCardListener());
        logInUI.addLogInEditBtn(new logInEditListener());
        logInUI.addSignUpBtn(new signUpListener());
        logInUI.addCancelBtn(new CancelListener());
        //card
        cardUI.addAnswerBtn(new answerListener());
        cardUI.addSubmitBtn(new submitListener());
        cardUI.addLogOutBtn(new logOutListener());
        //edit
        editUI.addUpdateBtn(new updateListener());
        editUI.addDeleteBtn(new deleteListener());
        editUI.addViewBtn(new viewListener());
        editUI.addLogOutBtn(new logOutListener());
        editUI.addBatchAddBtn(new batchAddListener());

    }
//===========================LogInUI Listener
    class logInCardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                User user = logInUI.getUser();
                User dbUser = model.searchUser(user.getAccount());
                if (dbUser==null)
                    return;
                if(user.identify(dbUser)){
                    logInUI.switcher(cardUI.getId(),logInUI.getLocation());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class logInEditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                User user = logInUI.getUser();
                User dbUser = model.searchUser(user.getAccount());
                if (dbUser==null)
                    return;
                if(user.identify(dbUser)){
                    logInUI.switcher(editUI.getId(),logInUI.getLocation());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class signUpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                User user = logInUI.getUser();
                if(!user.isIntact()) {
                    System.out.println("Please enter the Account and Password");
                    return;
                }
                User dbUser =  model.searchUser(user.getAccount());
                if(dbUser==null)
                    model.addUser(user);
                else
                    System.out.println("Already exist");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            logInUI.closeAll();
        }
    }
//===========================CardUI Listener
    class answerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardUI.setBackLabel(cardUI.getCard().getBack());
        }
    }
    class submitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Card card = cardUI.getCard();
            String score =cardUI.getScoreTf();
            String front = cardUI.getFrontLabel();
            try {
                //System.out.println(comboBox.getSelectedItem().toString());
                if (score.isEmpty() || front.compareTo("---") == 0) {
                    System.out.println("Please enter the Score and see answer first");
                }
                else{
                    model.submitCard(front,Integer.valueOf(score));
                    //get next card number
                    cardUI.setCard(model.getNextCart(cardUI.getComboBox().getSelectedItem().toString(),front));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            cardUI.setFrontLabel(cardUI.getCard().getFront());
            cardUI.setBackLabel("---");

        }
    }
    class logOutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardUI.switcher(logInUI.getId(),cardUI.getLocation());
        }
    }
//===========================EditUI Listener
    class updateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String front = editUI.getFrontTf();
            String back = editUI.getBackTf();
            try {
                if (!front.isEmpty() && !back.isEmpty())
                    model.setCard(front,back);
                else
                    System.out.println("Please input the Front and Back");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.deleteCard(editUI.getFrontTf());
        }
    }
    class viewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.viewCard();
        }
    }
    class batchAddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            File file = new File("data.txt");
            ArrayList<String> data = ReadFile(file);
            for(String record:data){
                List<String> list = String2Array(record);
                if(record.equals(""))
                    break;
                //String cmd = "insert into "+args[0]+" (front,back) values (\""+ list.get(0) +"\",\""+ list.get(1) +"\");";
                Card card = new Card(list.get(0),list.get(1));
                try {
                    model.addCard(card);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        ArrayList<String> ReadFile(File file) {
            String str = null;
            ArrayList<String> dst = new ArrayList<>();
            try {
                File fileDir = file;


                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_16);
                BufferedReader read = new BufferedReader(isr);


                while ((str = read.readLine()) != null) {
                    dst.add(str);
                    System.out.println(str);
                }

                read.close();

            }
            catch (UnsupportedEncodingException e)
            {
                System.out.println(e.getMessage());
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            return  dst;
        }
        List<String> String2Array(String src){
            String str[] = src.split(",");
            List<String> al = new ArrayList<String>();
            al = Arrays.asList(str);
            return al;
            //return (ArrayList<String>) Arrays.asList(src.split(","));

        }
    }
}
