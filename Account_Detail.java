import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account_Detail extends JFrame implements ActionListener {
    static JPanel jplt;
    static JButton  bt2;
    static long mob,ac_n;
    ResultSet rs;
    static JTable table = new JTable();
    static String name, ac_type;
    static long bal, all_ac;
    public Account_Detail(long mob, long ac_n) {
        Account_Detail.ac_n=ac_n;
        Account_Detail.mob =mob;
        Conn conn = new Conn();
        String q = "select * from users where mob_no =" + Account_Detail.mob + ";";
        try {
            rs = conn.s.executeQuery(q);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String[] colname = {"ACCOUNT NO", "Available Balance", "Account Type", "mob_no", "name"};
            model.setColumnIdentifiers(colname);
            while (rs.next()) {
                all_ac = rs.getLong(1);
                bal = rs.getLong(7);
                name = rs.getString(4);
                ac_type = rs.getString(8);
                long mb = rs.getLong(2);
                String[] row = {String.valueOf(all_ac), String.valueOf(bal), ac_type, String.valueOf(mb), name};
               /* String sp = String.valueOf(all_ac);
                cb = new String[]{sp};*/
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

        bt2 = new JButton("DONE");
        bt2.setBounds(800, 550, 80, 30);
        bt2.setBackground((Color.black));
        bt2.addActionListener(this);
        bt2.setForeground(Color.white);
        bt2.setFocusable(false);
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


