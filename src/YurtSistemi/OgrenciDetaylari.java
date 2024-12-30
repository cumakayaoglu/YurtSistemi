package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class OgrenciDetaylari extends JFrame implements ActionListener {

    Choice crollno;
    JTextField tckimlik;
    JTable table;
    JButton search, print, update, add, cancel;

    OgrenciDetaylari() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("TC Kimlik Numarası");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        tckimlik = new JTextField();
        tckimlik.setBounds(180, 20, 150, 20);
        add(tckimlik);


        String query = "SELECT * FROM Ogrenciler";
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

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
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

        add = new JButton("Ekle");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);

        update = new JButton("Güncelle");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Çıkış");
        cancel.setBounds(420, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String tcno = tckimlik.getText();

        if (ae.getSource() == search) {
            String query = "select * from ogrenciler where tckimlik = '"+tcno+"'";
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
            new GuncellemeOgrenci();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new OgrenciDetaylari();
    }
}