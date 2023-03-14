import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    static JTextField txt1;
    static JTextField txt2;
    static JButton bt_submit;
    static JButton bt_exit;
    static JLabel lbl1;
    static JLabel lbl2;
    static int pn;
    static JButton bt_signup = new JButton("SIGN-UP");
    ImageIcon img = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\Inkedbank-icon7890.jpg");

    public Login() {
        super("TCG-BANK");
        this.setBounds(50, 50, 1000, 1000);
        this.getContentPane().setBackground(Color.gray);
        this.setLayout(null);
        JPanel pnl = new JPanel();
        pnl.setBounds(200, 100, 500, 400);
        pnl.setBackground(Color.white);
        pnl.setLayout(null);
        this.add(pnl);
        JLabel lbl_im = new JLabel(this.img);
        lbl_im.setBounds(160, 0, 180, 180);
        pnl.add(lbl_im);
        lbl1 = new JLabel("ACCOUNT NO :-");
        lbl1.setBounds(40, 180, 150, 30);
        pnl.add(lbl1);
        lbl2 = new JLabel("PIN NO.  :-");
        lbl2.setBounds(40, 250, 150, 30);
        pnl.add(lbl2);
        txt1 = new JTextField();
        txt1.setBounds(180, 180, 250, 30);
        txt1.setBackground(Color.cyan);
        pnl.add(txt1);
        txt2 = new JTextField();
        txt2.setBounds(180, 250, 250, 30);
        txt2.setBackground(Color.cyan);
        pnl.add(txt2);
        bt_submit = new JButton("SUBMIT");
        bt_submit.setBounds(70, 300, 100, 30);
        bt_submit.addActionListener(this);
        pnl.add(bt_submit);
        bt_signup.setBounds(200, 300, 100, 30);
        bt_signup.addActionListener(this);
        pnl.add(bt_signup);
        bt_exit = new JButton("EXIT");
        bt_exit.setBounds(330, 300, 100, 30);
        bt_exit.addActionListener(this);
        pnl.add(bt_exit);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Conn conn = new Conn();
        if (e.getSource() == bt_submit) {
            String ac = txt1.getText();
            String get_pin = txt2.getText();
            if (!ac.equals("") && !get_pin.equals("")) {
                int ac_n = Integer.parseInt(ac);
                String q = "select * from users where ac_no =" + ac_n + ";";

                try {
                    ResultSet rs = conn.s.executeQuery(q);
                    while(rs.next()) {
                        pn = rs.getInt(3);
                    }
                    int pn_user = Integer.parseInt(get_pin);
                    if (pn_user == pn) {
                        this.setVisible(false);
                        new Home_Page(ac_n);
                    } else {
                        JOptionPane.showMessageDialog(this, "INVALID ACCOUNT .");
                    }
                } catch (SQLException var9) {
                    throw new RuntimeException(var9);
                }
            } else {
                JOptionPane.showMessageDialog(this, "FILL ALL FIELDS.");
                txt1.setBackground(Color.white);
                txt2.setBackground(Color.white);
            }
        } else if (e.getSource() == bt_signup) {
            this.setVisible(false);
            new Signup_page();
        } else if (e.getSource() == bt_exit) {
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}
