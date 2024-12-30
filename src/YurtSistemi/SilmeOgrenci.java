package YurtSistemi;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SilmeOgrenci extends JFrame implements ActionListener {
    JTable table;
    JTextField tckimlik;
    JButton search, print, update, delete, cancel;

    SilmeOgrenci(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("TC KİMLİK NO");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        tckimlik = new JTextField();
        tckimlik.setBounds(180, 20, 150, 20);
        add(tckimlik);

        table = new JTable();
        String query = "SELECT tckimlik FROM Ogrenciler";
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
        String queryy = "SELECT * FROM ogrenciler";
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

        cancel = new JButton("Çıkış");
        cancel.setBounds(220, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        delete = new JButton("Kayıt Sil");
        delete.setBounds(320, 70, 80, 20);
        delete.addActionListener(this);
        add(delete);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String tcno = tckimlik.getText();

        if (ae.getSource() == search) {
            String query = "select * from ogrenciler where tckimlik = '" + tcno + "'";
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new GuncellemeOgrenci();
        }
        else if (ae.getSource() == delete) {
            String query = "DELETE FROM ogrenciler WHERE tckimlik = '" + tcno + "'";
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                Statement stmt = conn.createStatement();
                int rowsAffected = stmt.executeUpdate(query);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Kayıt başarıyla silindi.");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Silme işlemi başarısız. Kayıt bulunamadı.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Bir hata oluştu: " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new SilmeOgrenci();
    }
}