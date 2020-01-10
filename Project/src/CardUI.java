import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class CardUI extends uiController{
    //private JFrame frame = new JFrame("FlashCard");

    //ArrayList<String> card = new ArrayList<String>();
    private Card card = new Card("問題", "Question");
    //obj for card
    private JPanel cardPanel = new JPanel();
    private GridBagLayout layout = new GridBagLayout();

    public String getScoreTf() {
        return scoreTf.getText();
    }

    public void setScoreTf(String text) {
        this.scoreTf.setText(text);
    }

    private JTextField scoreTf = new JTextField(10);
    private JLabel fTitleLabel = new JLabel("Front");
    private JLabel bTitleLabel = new JLabel("Back");
    private JLabel sTitleLabel = new JLabel("Score");
    private JLabel frontLabel = new JLabel((new String("問題".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)));
    private JLabel backLabel = new JLabel("---");
    private JButton answerBtn = new JButton("Answer");
    private JButton submitBtn = new JButton("Submit");
    private JButton logOutBtn = new JButton("Log Out");

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    private JComboBox<String> comboBox = new JComboBox<String>();

    void setCard(Card card){this.card = card;}
    Card getCard(){return this.card;}
    void setBackLabel(String text){backLabel.setText(text);}
    void  setFrontLabel(String text){frontLabel.setText(text);}
    String  getFrontLabel(){return frontLabel.getText();}
    CardUI(int id,String title) throws UnsupportedEncodingException {
        super(id,title);

        //card obj
        cardPanel.setLayout(layout);
        cardPanel.add(fTitleLabel);
        cardPanel.add(bTitleLabel);
        cardPanel.add(sTitleLabel);
        cardPanel.add(frontLabel);
        cardPanel.add(backLabel);
        cardPanel.add(scoreTf);
        cardPanel.add(answerBtn);
        cardPanel.add(submitBtn);
        cardPanel.add(logOutBtn);
        cardPanel.add(comboBox);
        comboBox.addItem("order");
        comboBox.addItem("random");
        comboBox.addItem("review");
        //card layout
        GridBagConstraints setting = new GridBagConstraints();
        setting.fill = GridBagConstraints.NONE;
        setting.weightx = 1;//width extend
        setting.weighty = 0;//height don't extend
        setting.gridwidth = 1;//width size
        layout.setConstraints(fTitleLabel, setting);
        layout.setConstraints(bTitleLabel, setting);
        setting.gridwidth = 0;//last one=> \n
        layout.setConstraints(sTitleLabel, setting);
        setting.gridwidth = 1;
        setting.weighty = 1;
        layout.setConstraints(frontLabel, setting);
        layout.setConstraints(backLabel, setting);
        setting.gridwidth = 0;
        layout.setConstraints(scoreTf, setting);//scord


        //frame add
        this.getContentPane().add(BorderLayout.CENTER,cardPanel);
    }
    void addLogOutBtn(ActionListener listener){
        logOutBtn.addActionListener(listener);
    }
    void addSubmitBtn(ActionListener listener){
        submitBtn.addActionListener(listener);
    }
    void addAnswerBtn(ActionListener listener) { answerBtn.addActionListener(listener); }

}
