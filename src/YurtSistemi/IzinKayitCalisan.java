package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class IzinKayitCalisan extends JFrame implements ActionListener {

    JTextField calisanid;
    JTable table;
    JButton search, print, update, add, cancel;

    IzinKayitCalisan() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Çalışan ID");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        calisanid = new JTextField();
        calisanid.setBounds(180, 20, 150, 20);
        add(calisanid);

        table = new JTable();

        String query = "SELECT calisanid FROM calisanlar";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable();

        String queryy = "SELECT * FROM izinler_calisan";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryy);
            rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        search = new JButton("Ara");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Yazdır");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("İptal");
        cancel.setBounds(220, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }




    public void actionPerformed(ActionEvent ae) {
        String calisanID = calisanid.getText();

        if (ae.getSource() == search) {
            String query = "select * from izinler_calisan where calisanid = '"+calisanID+"'";
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddStudent();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new GuncellemeCalisan();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new IzinKayitCalisan();
    }
}