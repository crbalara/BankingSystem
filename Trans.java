import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Trans extends JFrame implements ActionListener {
    static  JButton bt_ok;
    static JTable table=new JTable();
    static  JPanel jplt=new JPanel();
   ResultSet rs;
   static int sr_no;
   static long ac_no,old_bl,add_bl,total_bl,ac_n,total_bal;
   static String type_trans,p;
   int flag1,flag2;

    public Trans (long ac_n){
        super( "TRANSACTION HISTORY");
        Trans.ac_n=ac_n;
        System.out.println("trans 1st ac_n"+ac_n);
        Conn conn = new Conn();
        String q = "select * from users where ac_no =" + Trans.ac_n + ";";
        try {
            rs = conn.s.executeQuery(q);
            while (rs.next()) {
                total_bal = rs.getLong(7);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        flag1=Home_Page.flag;
       if(flag1==1) {
            p = "select * from trans where ac_no =" + Trans.ac_n + ";";
       }
       else {
            p = "select * from trans where sr_no=(select max(sr_no) from trans) and ac_no=" + Trans.ac_n + ";";
       }
        System.out.println(p);
        try {
            rs = conn.s.executeQuery(p);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String[] colname = {"SR_NO", "AC_NO ", "OLD_BL", "ADD_BL", "TOTAL_BL","TYPE"};
            model.setColumnIdentifiers(colname);
            while (rs.next()) {
                sr_no = rs.getInt(1);
                ac_no = rs.getLong(2);
                old_bl = rs.getLong(3);
                add_bl = rs.getLong(4);
                 total_bl = rs.getLong(5);
                 type_trans=rs.getString(6);
                String[] row = {String.valueOf(sr_no), String.valueOf(ac_no), String.valueOf(old_bl), String.valueOf(add_bl), String.valueOf(total_bl),type_trans};
                model.addRow(row);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        setBounds(50, 50, 1000, 900);
        getContentPane().setBackground(Color.white);

        setLayout(null);
        jplt=new JPanel();
        jplt.setBounds(50, 50, 720, 700);
        jplt.setBackground(Color.white);
        jplt.add(new JPanel());
        add(jplt);

        table.setBounds(20, 20, 500, 500);
        table.setBorder(null);
        table.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        jplt.add(new JScrollPane(table));

        bt_ok = new JButton("DONE");
        bt_ok.setBounds(800, 550, 80, 30);
        bt_ok.setBackground((Color.black));
        bt_ok.addActionListener(this);
        bt_ok.setForeground(Color.white);
        bt_ok.setFocusable(false);
        add(bt_ok);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==bt_ok){
            setVisible (false);
            new Home_Page(ac_n);
            System.out.println("trns last"+ac_n);
        }
    }

}