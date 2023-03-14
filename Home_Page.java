import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class Home_Page extends JFrame implements ActionListener {
    static JButton bt_user_hp, bt_logout_hp, bt_acc_dtl, bt_dp_cash, bt_wd_cash, bt_trf_mny,bt_trans;

    static JLabel  lbl_bname_hp,lbl_bnk_pic,lbl_dtl,lbl_dpt,lbl_wd,lbl_chk;
    static ResultSet rs;
    static String name;
    static int flag=0;
    static long  mob,ac_n,total_balance;
    public Home_Page(long ac_no) {
        super("USER");
        ac_n = ac_no;


        try {
            Conn conn = new Conn();
            String q = "select * from users where ac_no =" + ac_n + ";";
            rs = conn.s.executeQuery(q);
            while (rs.next()) {
                mob = rs.getLong(2);
                name = rs.getString(4);
                total_balance=rs.getLong(7);
            }
        } catch (Exception ex) {
            System.out.println("Exception IN HOME PAGE");
        }
        setBounds(50, 50, 1000, 900);
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);

        lbl_bname_hp = new JLabel("TCG BANK");
        lbl_bname_hp.setBounds(220, 5, 250, 70);
        lbl_bname_hp.setFont(new Font("Arial", Font.BOLD, 30));
        lbl_bname_hp.setForeground(Color.white);
        add(lbl_bname_hp);

        ImageIcon img_usr = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\admin30.jpg");
        bt_user_hp = new JButton(name, img_usr);
        bt_user_hp.setBounds(600, 25, 150, 35);
        bt_user_hp.setBackground(Color.white);
        bt_user_hp.addActionListener(this);
        bt_user_hp.setFocusable(true);
        add(bt_user_hp);

        ImageIcon img_logout = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\logout30.png");
        bt_logout_hp = new JButton("   [LogOut]", img_logout);
        bt_logout_hp.setBounds(800, 25, 150, 35);
        bt_logout_hp.setBackground(Color.white);
        bt_logout_hp.addActionListener(this);
        add(bt_logout_hp);

        ImageIcon img_bnk = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\Inkedbank-icon500.jpg");
        lbl_bnk_pic=new JLabel(img_bnk);
        lbl_bnk_pic.setBounds(75,140,450,430);
        add(lbl_bnk_pic);

        ImageIcon img_acc = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\ac130.png");
        bt_acc_dtl = new JButton(img_acc);
        bt_acc_dtl.setBounds(600,150,150,150);
        add(bt_acc_dtl);

        bt_acc_dtl.addActionListener(this);
        bt_acc_dtl.setBackground(Color.white);

        lbl_dtl=new JLabel("       ACCOUNT DETAILS");
        lbl_dtl.setBounds(600,310,150,20);
        lbl_dtl.setForeground(Color.white);
        add(lbl_dtl);

        ImageIcon img_dp_cash = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\dip130.png");
        bt_dp_cash = new JButton( img_dp_cash);
        bt_dp_cash.setBounds(800,150,150,150);
        bt_dp_cash.addActionListener(this);
        bt_dp_cash.setBackground(Color.white);
        add(bt_dp_cash);

        bt_trans =new JButton("TRANSACTION");
        bt_trans.setBounds(10,25,150,30);
        bt_trans.addActionListener(this);
        bt_trans.setBackground(Color.white);
        add(bt_trans);

        lbl_dpt=new JLabel("          DEPOSIT CASH");
        lbl_dpt.setBounds(800,310,150,20);
        lbl_dpt.setForeground(Color.white);
        add(lbl_dpt);

        ImageIcon img_wd_cash = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\WIDH130.png");
        bt_wd_cash = new JButton(img_wd_cash);
        bt_wd_cash.setBounds(600,400,150,150);
        bt_wd_cash.addActionListener(this);
        bt_wd_cash.setBackground(Color.white);
        add(bt_wd_cash);

        lbl_wd=new JLabel("         WITHDRAW CASH");
        lbl_wd.setBounds(600,560,150,20);
        lbl_wd.setForeground(Color.white);
        add(lbl_wd);

        ImageIcon img_trf_mny = new ImageIcon("C:\\Users\\admin\\Desktop\\bank\\check_bll100.png");
         bt_trf_mny = new JButton(img_trf_mny);
        bt_trf_mny.setBounds(800,400,150,150);
       add(bt_trf_mny);

        bt_trf_mny.addActionListener(this);
        bt_trf_mny.setBackground(Color.white);

        lbl_chk=new JLabel("          CHECK BALANCE");
        lbl_chk.setBounds(800,560,150,20);
        lbl_chk.setForeground(Color.white);
        add(lbl_chk);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt_logout_hp) {
                setVisible(false);
                new Login();
            }
      else  if (e.getSource() == bt_acc_dtl) {
            setVisible(false);
           new Account_Detail(mob,ac_n);
        }
            else if (e.getSource() == bt_dp_cash) {
                setVisible(false);
                 new Diposit(ac_n);
            }
       else if (e.getSource() == bt_user_hp) {
             setVisible(false);
             new User_Detail(ac_n);
        }
        else if (e.getSource() == bt_wd_cash) {
                 setVisible(false);
                 new Withdrawal(ac_n);
             }
       else if (e.getSource() == bt_trf_mny) {
           try {
            Conn conn = new Conn();
            String q = "select * from users where ac_no =" + ac_n + ";";
            rs = conn.s.executeQuery(q);
            while (rs.next()) {
           total_balance=rs.getLong(7);
            }
        } catch (Exception ex) {
            System.out.println("Exception IN HOME PAGE");
        }
           JOptionPane.showMessageDialog(this, "YOUR CURRENT BALANCE IS : "+total_balance+" /-");
        }
       else if (e.getSource() == bt_trans)
        {
            flag++;
            setVisible(false);
          new Trans (ac_n);

            System.out.println("home_page"+ac_n);
        }
    }
    }






