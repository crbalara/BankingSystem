import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup_page extends JFrame implements ActionListener {
    static JTextField txt1, txt2, txt3;
    static JRadioButton male_bt,fmale_bt;
    static JLabel lbl1,lbl2,lbl3,zender,warning;
   static ButtonGroup group1 = new ButtonGroup();
    static JButton bt_submit;
    static JButton bt_exit=new JButton("EXIT");
    static String[] type ={"SELECT AC TYPE","SAVING","CURRENT","JOINT"};
    static JComboBox<String> box=new JComboBox<>(type);
    ImageIcon img = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\Inkedbank-icon7890.jpg");
int pn;
long ac_no;
        public Signup_page() {
        super("SIGN-UP");

        setBounds(50, 50, 1000, 1000);
        getContentPane().setBackground(Color.gray);
        setLayout(null);

        JPanel pnl = new JPanel();
        pnl.setBounds(200, 100, 500, 600);
        pnl.setBackground(Color.white);
        pnl.setLayout(null);
        add(pnl);

        JLabel lbl_im = new JLabel(img);
        lbl_im.setBounds(160, 0, 180, 180);
        pnl.add(lbl_im);

        lbl1 = new JLabel("ENTER YOUR NAME:-");
        lbl1.setBounds(20, 180, 150, 30);
        pnl.add(lbl1);

        lbl2 = new JLabel("ENTER YOUR MOBILE NO :-");
        lbl2.setBounds(20, 240, 150, 30);
        pnl.add(lbl2);

        lbl3 = new JLabel("ENTER YOUR CITY:-");
        lbl3.setBounds(20, 300, 150, 30);
        pnl.add(lbl3);

        txt1 = new JTextField();
        txt1.setBounds(180, 180, 300, 30);
        txt1.setBackground(Color.cyan);
        pnl.add(txt1);

        txt2 = new JTextField();
        txt2.setBounds(180, 240, 300, 30);
        txt2.setBackground(Color.cyan);
        pnl.add(txt2);

        txt3 = new JTextField();
        txt3.setBounds(180, 300, 300, 30);
        txt3.setBackground(Color.cyan);
        pnl.add(txt3);

        zender=new JLabel("GENDER:-");
        zender.setBounds(20, 370, 90, 30);
        pnl.add(zender);

        male_bt=new JRadioButton("MALE");
        male_bt.setBounds(180, 370, 100, 30);
        pnl.add(male_bt);

        fmale_bt=new JRadioButton("FEMALE");
        fmale_bt.setBounds(300, 370, 100, 30);
        pnl.add(fmale_bt);

        box.setBounds(50,430,100,30);
        pnl.add(box);

        group1.add(male_bt);
        group1.add(fmale_bt);

        bt_submit = new JButton("SUBMIT");
        bt_submit.setBounds(200, 430, 100, 30);
        bt_submit.addActionListener(this);
        pnl.add(bt_submit);

        bt_exit.setBounds(350, 430, 100, 30);
        bt_exit.addActionListener(this);
        pnl.add(bt_exit);

        warning=new JLabel();
        warning.setBounds(0, 480, 500, 30);
        warning.setForeground(Color.red);
        warning.setFont(new Font("Verdana", Font.BOLD, 20));
        pnl.add(warning);

        Random rn=new Random();
        pn=1000+rn.nextInt(9999-1000);

        Random rm=new Random();
        ac_no=100000+rm.nextInt(999999-100000);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = txt1.getText();
        String mob = txt2.getText();
        String city = txt3.getText();
        String zen = "";
        String type= box.getItemAt(box.getSelectedIndex());
        long final_bl=0;
        if (e.getSource() == bt_submit) {
            if (male_bt.isSelected()) {
                zen = "male";
            }
            if (fmale_bt.isSelected()) {
                zen = "female";
            }
            if (name.equals("") || mob.equals("") || city.equals("") || zen.equals("")) {
                warning.setForeground(Color.red);
                warning.setText("PLEASE! FILL ALL FIELD");
            } else {
                Conn con = new Conn();
                try {
                    String q = "INSERT INTO users (ac_no,mob_no,pin,name,address,gender,balance,ac_type) VALUES("+ac_no+","+ mob + "," + pn + ",'" + name + "','" + city + "','" + zen + "'," + final_bl + ",'"+type+"');";
                   con.s.executeUpdate(q);
                    warning.setForeground(Color.green);
                    warning.setText("SUBMITTED SUCCESSFULLY");
                    JOptionPane.showMessageDialog(this, "YOUR AC NO IS:-" + ac_no + "\nYOUR PIN IS:-" + pn);
                    setVisible(false);
                     new Login();
                } catch (Exception ex) {
                    System.out.println("EXCEPTION IN SIGN_PAGE");
                }
            }
        }
        else if (e.getSource() == bt_exit) {
                setVisible(false);
                    new Login();
            }

    }
}

