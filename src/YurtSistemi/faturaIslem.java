package YurtSistemi;



import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class faturaIslem extends JFrame implements ActionListener {


    JTextField tckimlik;
    JTextField crollno;
    JComboBox cbcourse, cbbranch, cbsemester;
    JLabel labeltotal;
    JButton update, pay, back;

    faturaIslem() {
        setSize(900, 500);
        setLocation(300, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("iconss/odeme.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);

        JLabel heading = new JLabel("TC KİMLİK NO");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        crollno = new JTextField();
        crollno .setBounds(180, 20, 150, 20);
        add(crollno );



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


        JLabel lblname = new JLabel("Ad");
        lblname.setBounds(40, 100, 150, 20);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblname);

        JLabel labelname = new JLabel();
        labelname.setBounds(200, 100, 150, 20);
        labelname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelname);

        JLabel lblfname = new JLabel("Soyad");
        lblfname.setBounds(40, 140, 150, 20);
        lblfname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblfname);

        JLabel labelfname = new JLabel();
        labelfname.setBounds(200, 140, 150, 20);
        labelfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelfname);

        JButton fetch = new JButton("Ara");
        fetch.setBackground(Color.BLACK);
        fetch.setForeground(Color.WHITE);
        fetch.setBounds(270, 380, 100, 25);
        fetch.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/YurtSistemi", "postgres", "5478963210cuma");
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM ogrenciler WHERE tckimlik = ?")) {

                String rollno = crollno.getText();
                if (rollno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Roll Number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ps.setString(1, rollno);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    labelname.setText(rs.getString("ad") != null ? rs.getString("ad") : "Not Available");
                    labelfname.setText(rs.getString("soyad") != null ? rs.getString("soyad") : "Not Available");




                } else {
                    JOptionPane.showMessageDialog(null, "Roll Number not found");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(fetch);






        JLabel lblcourse = new JLabel("Kayit Dönemi");
        lblcourse.setBounds(40, 180, 150, 20);
        lblcourse.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblcourse);

        String course[] = {"sabit_fiyat", "erken_odeme","gec_odeme"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(200, 180, 150, 20);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);



        JLabel lblsemester = new JLabel("Dönem");
        lblsemester.setBounds(40, 260, 150, 20);
        lblsemester.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblsemester);

        String semester[] = {"donem1", "donem2" };
        cbsemester = new JComboBox(semester);
        cbsemester.setBounds(200, 260, 150, 20);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        JLabel lbltotal = new JLabel("Toplam Tutar");
        lbltotal.setBounds(40, 300, 150, 20);
        lbltotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lbltotal);

        labeltotal = new JLabel();
        labeltotal.setBounds(200, 300, 150, 20);
        labeltotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labeltotal);

        update = new JButton("Güncelle");
        update.setBounds(30, 380, 100, 25);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        pay = new JButton("Öde");
        pay.setBounds(150, 380, 100, 25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        pay.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(pay);

        back = new JButton("Çıkış");
        back.setBounds(390, 380, 100, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(back);

        setVisible(true);
    }

    // ResultSet rs = c.s.executeQuery("select * from fatura where kayit_donemi = '"+course+"'");


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String course = (String) cbcourse.getSelectedItem();
            String semester = (String) cbsemester.getSelectedItem();
            String query = "select * from faturalar where odemetipi = '"+course+"'";
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while(rs.next()) {
                    labeltotal.setText(rs.getString(semester));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == pay) {
            String rollno = crollno.getText();
            String course = (String) cbcourse.getSelectedItem();
            String semester = (String) cbsemester.getSelectedItem();
            // String branch = (String) cbbranch.getSelectedItem();
            String total = labeltotal.getText();
            // String query = "insert into collegefee values('"+rollno+"', '"+course+"', '"+branch+"', '"+semester+"', '"+total+"')";
            //JOptionPane.showMessageDialog(null, "College fee submitted successfully");
            //setVisible(false);
            String query = "INSERT INTO odemeler (tckimlik, odemetipi, donem, odememiktar) VALUES (?, ?, ?, ?) ON CONFLICT (tckimlik,donem) DO NOTHING;";
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/YurtSistemi", "postgres", "5478963210cuma");
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, rollno);     // 1. parametre: rollno
                ps.setString(2, course);     // 2. parametre: course
                ps.setString(3, semester);   // 3. parametre: semester
                ps.setString(4, total);      // 4. parametre: total

                int rowsAffected = ps.executeUpdate(); // Sorguyu çalıştır
                if (rowsAffected > 0) {
                    System.out.println("Record inserted successfully!");
                } else {
                    //System.out.println("No record inserted.");
                    JOptionPane.showMessageDialog(null, "Database error: " +"No record inserted." , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new faturaIslem();
    }
}