import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Diposit extends JFrame implements ActionListener {
    static JLabel lbl_dp, lbl_bl;
    static JButton bt1, bt2;
    static JTextField txt;
    static long ac_n,total_balance;
    ResultSet rs;

    public Diposit(long ac_n) {
        Diposit.ac_n =ac_n;

        setBounds(50, 50, 1000, 900);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        lbl_dp = new JLabel("DEPOSIT CASH");
        lbl_dp.setBounds(400, 20, 400, 70);
        lbl_dp.setFont(new Font("Arial", Font.BOLD, 30));
        add(lbl_dp);

        lbl_bl = new JLabel("ENTER AMOUNT :-");
        lbl_bl.setBounds(300, 150, 100, 30);
        add(lbl_bl);

        txt = new JTextField();
        txt.setBounds(410, 150, 300, 30);
        txt.setBackground((Color.cyan));
        add(txt);

        bt1 = new JButton("SUBMIT");
        bt1.setBounds(350, 200, 100, 30);
        bt1.setBackground((Color.black));
        bt1.addActionListener(this);
        bt1.setForeground(Color.white);
        bt1.setFocusable(false);
        add(bt1);

        bt2 = new JButton("EXIT");
        bt2.setBounds(550, 200, 100, 30);
        bt2.setBackground((Color.black));
        bt2.addActionListener(this);
        bt2.setForeground(Color.white);
        bt2.setFocusable(false);
        add(bt2);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==bt1) {
            long amount= Long.parseLong(txt.getText());
            Conn conn = new Conn();
            try {
                String q = "select * from users where ac_no =" + ac_n + ";";
                rs = conn.s.executeQuery(q);
                while (rs.next()) {
                    total_balance=rs.getLong(7);
                }
            } catch (Exception ex) {
                System.out.println("Exception IN HOME PAGE");
            }
            String m = "update USERS set balance = balance +"+ amount +" where ac_no=  "+ ac_n  ;
            String t= "INSERT INTO trans (ac_no,old_bl,add_bl,total_balance,type) VALUES("+ac_n+","+ total_balance + "," + amount + "," + (total_balance+amount) + ",'" +"DEBIT"+"');" ;
            System.out.println(t);

            try {
                conn.s.executeUpdate(m);
                conn.s.executeUpdate(t);
                JOptionPane.showMessageDialog(this, "SUCCESFULLY DABITED :- "+ amount+" /-");
                setVisible(false);
                new Home_Page(ac_n);
            } catch(SQLException ex){
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() ==bt2) {
            setVisible(false);
            new Home_Page(ac_n);
        }
    }
}
