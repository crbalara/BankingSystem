import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Detail extends JFrame implements ActionListener {

    static JLabel  lbl_main,lbl_ac_no, lbl_mob_no, lbl_pin, lbl_name, lbl_address, lbl_gender, lbl_ac_type;
    static JButton  bt2;
    static long ac_n;
    long mob1;
    int pin1;
   static String ac_r2;
   // static String name1;
    static String address1 ;
    static String gender1;
    static  String ac_type1;
     public User_Detail(long ac_n) {
        User_Detail.ac_n = ac_n;
        lbl_ac_no = new JLabel();
        lbl_mob_no = new JLabel();
        lbl_pin = new JLabel();
        lbl_name = new JLabel();
        lbl_address = new JLabel();
        lbl_gender = new JLabel();
        lbl_ac_type = new JLabel();
        try {
            Conn conn = new Conn();
            ResultSet rss;
            String p = "select * from users where ac_no =" + ac_n + ";";
            rss = conn.s.executeQuery(p);
            while (rss.next()) {
                mob1 = rss.getLong(2);
                pin1 = rss.getInt(3);
                String name1 = rss.getString(4);
                address1 = rss.getString(5);
                gender1 = rss.getString(6);
                ac_type1 = rss.getString(8);
                ac_r2 = "AC NO. :-" + ac_n;
                lbl_ac_no.setText(ac_r2);
                lbl_mob_no.setText("NAME :-" + name1);
                lbl_pin.setText("PIN :-" + pin1);
                lbl_name.setText("MOB NO. :-" + mob1);
                lbl_address.setText("ADDRESS :-" + address1);
                lbl_gender.setText("GENDER :-" + gender1);
                lbl_ac_type.setText("AC TYPE :-" + ac_type1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        setBounds(50, 50, 1000, 800);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        lbl_main = new JLabel("CUSTOMER PROFILE");
        lbl_main.setBounds(350, 50, 350, 50);
        lbl_main.setBackground(Color.black);
        lbl_main.setFont(new Font("Arial", Font.BOLD, 30));
        add(lbl_main);


        lbl_ac_no.setBounds(150, 150, 300, 30);
        lbl_ac_no.setBackground(Color.black);
        lbl_ac_no.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_ac_no);


        lbl_mob_no.setBounds(150, 210, 300, 30);
        lbl_mob_no.setBackground(Color.black);
        lbl_mob_no.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_mob_no);

        lbl_pin.setBounds(150, 270, 300, 30);
        lbl_pin.setBackground(Color.black);
        lbl_pin.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_pin);

        lbl_name.setBounds(150, 330, 300, 30);
        lbl_name.setBackground(Color.black);
        lbl_name.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_name);

        lbl_address.setBounds(150, 390, 300, 30);
        lbl_address.setBackground(Color.black);
        lbl_address.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_address);

        lbl_gender.setBounds(150, 450, 300, 30);
        lbl_gender.setBackground(Color.black);
        lbl_gender.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_gender);

        lbl_ac_type.setBounds(150, 510, 300, 30);
        lbl_ac_type.setBackground(Color.black);
        lbl_ac_type.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbl_ac_type);

        bt2 = new JButton("DONE");
        bt2.setBounds(450, 580, 80, 30);
        bt2.setBackground((Color.black));
        bt2.setForeground(Color.white);
        bt2.setFocusable(false);
        bt2.addActionListener(this);
        add(bt2);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt2) {
            setVisible(false);
            new Home_Page(ac_n);
        }
    }
}


