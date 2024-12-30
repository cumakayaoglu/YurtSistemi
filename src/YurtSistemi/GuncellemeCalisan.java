package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class GuncellemeCalisan extends JFrame implements ActionListener {

    JTextField tfcourse, tfaddress, tfphone, tfemail, tfbranch, crollno,tfposition,tfsifre;
    JLabel labelrollno , labeldob,labelsifre;
    JButton submit, cancel;

    GuncellemeCalisan() {

        setSize(900, 650);
        setLocation(350, 50);

        setLayout(null);

        JLabel heading = new JLabel("Çalışan Güncelleme Detayları");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblrollnumber = new JLabel("TC Kimlik Giriniz");
        lblrollnumber.setBounds(50, 100, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);

        crollno = new JTextField();
        crollno.setBounds(250, 100, 200, 20);
        add(crollno);

        JLabel lblname = new JLabel("Ad");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        JLabel labelname = new JLabel();
        labelname.setBounds(200, 150, 150, 30);
        labelname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelname);

        JLabel lblfname = new JLabel("Soyad");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

        JLabel labelfname = new JLabel();
        labelfname.setBounds(600, 150, 150, 30);
        labelfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelfname);

        JLabel lblrollno = new JLabel("TC Kimlik");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);

        labelrollno = new JLabel();
        labelrollno.setBounds(200, 200, 200, 30);
        labelrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelrollno);

        JLabel lbldob = new JLabel("Doğum Tarihi");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);

        labeldob = new JLabel();
        labeldob.setBounds(600, 200, 200, 30);
        labeldob.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldob);

        JLabel lbladdress = new JLabel("Adres");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Telefon");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email ");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel lblsifre = new JLabel("Sifre");
        lblsifre.setBounds(50, 350, 200, 30);
        lblsifre.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsifre);


        labelsifre = new JLabel();
        labelsifre.setBounds(200, 350, 150, 30);
        labelsifre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelsifre);

        JLabel lblposition = new JLabel("Pozisyon");
        lblposition.setBounds(400, 300, 200, 30); // Daha aşağı bir konuma alıyoruz
        lblposition.setFont(new Font("serif", Font.BOLD, 20));
        add(lblposition);


        JLabel lbposition = new JLabel();
        lbposition.setBounds(600, 300, 150, 30);
        lbposition.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbposition);



        JButton fetch = new JButton("Ara");
        fetch.setBounds(500, 100, 100, 30);
        fetch.addActionListener(e -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/YurtSistemi", "postgres", "5478963210cuma");
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM calisanlar WHERE calisanid = ?")) {

                String rollno = crollno.getText().trim();
                if (rollno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Roll Number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ps.setString(1, rollno);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    labelname.setText(rs.getString("ad") != null ? rs.getString("ad") : "Not Available");
                    labelfname.setText(rs.getString("soyad") != null ? rs.getString("soyad") : "Not Available");
                    labelrollno.setText(rs.getString("calisanid") != null ? rs.getString("calisanid") : "Not Available");
                    tfaddress.setText(rs.getString("adres") != null ? rs.getString("adres") : "Not Available");
                    tfphone.setText(rs.getString("telno") != null ? rs.getString("telno") : "Not Available");
                    tfemail.setText(rs.getString("email") != null ? rs.getString("email") : "Not Available");
                    lbposition.setText(rs.getString("pozisyon") != null ? rs.getString("pozisyon") : "Not Available");
                    labelsifre.setText(rs.getString("sifre") != null ? rs.getString("sifre") : "Not Available");

                    // Tarih bilgisini almak ve formatlamak
                    Date dob = rs.getDate("dob");
                    if (dob != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("tr", "TR"));
                        labeldob.setText(sdf.format(dob)); // 07 Kas 2024
                    } else {
                        labeldob.setText("Not Available");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Roll Number not found");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(fetch);

        submit = new JButton("Güncelle");
        submit.setBounds(250, 500, 120, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Çıkış");
        cancel.setBounds(450, 500, 120, 30);
        cancel.addActionListener(e -> setVisible(false));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {

            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();





            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/YurtSistemi", "postgres", "5478963210cuma");
                 //  PreparedStatement ps = conn.prepareStatement("UPDATE ogrenciler1 SET adres = ?, phone = ?, email = ?, course = ?, branch = ? WHERE rollno = ?"))
                 PreparedStatement ps = conn.prepareStatement("UPDATE calisanlar SET adres = ?, telno = ?, email = ?  WHERE calisanid ='"+crollno.getText()+"'")){
                // PreparedStatement ps = conn.prepareStatement("UPDATE ogrenciler1 SET adres = ?")) {

                ps.setString(1, address);
                ps.setString(2, phone);
                ps.setString(3, email);



                int updated = ps.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Roll Number not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new GuncellemeCalisan();
    }
}