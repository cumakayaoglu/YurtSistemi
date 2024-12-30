package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;


public class AddStudent extends JFrame implements ActionListener {
    JTextField tfname, tffname, tfaddress, tfphone, tfemail,tfsifre;
    JTextField labelrollno;
    JDateChooser dcdob;
    JButton submit, cancel;

    AddStudent(){

        setSize(900, 700);
        setLocation(350, 50);

        setLayout(null);

        JLabel heading = new JLabel("Yeni Öğrenci Kaydı");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel lblname = new JLabel("İsim");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);

        JLabel lblfname = new JLabel("Soyisim");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel lblrollno = new JLabel("TC Kimlik No");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);

        labelrollno = new JTextField();
        labelrollno.setBounds(200, 200, 150, 30);
        labelrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(labelrollno);

        JLabel lbldob = new JLabel("Doğum Tarihi");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);

        JLabel lbladdress = new JLabel("Adres");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Telefon No");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel lblsifre = new JLabel("Sifre");
        lblsifre.setBounds(400, 300, 200, 30);
        lblsifre.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsifre);

        tfsifre = new JTextField();
        tfsifre.setBounds(600, 300, 150, 30);
        add(tfsifre);


        submit = new JButton("Kaydet");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("İptal");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String rollno = labelrollno.getText();
            String adres = tfaddress.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String phone = tfphone.getText();
            String sifre = tfsifre.getText();
            String email = tfemail.getText();

            dob = convertTurkishDateToEnglish(dob);
            //String query = "insert into student (name,fname,tcno,dob,adres,phone,email) VALUES (?,?,?,?,?,?,?)";
            String query = "INSERT INTO ogrenciler (tckimlik, ad, soyad, telno, email, adres,sifre,dob) " +
                    "VALUES (?, ?, ?, ?, ?,?,?,TO_DATE(?, 'DD Mon YYYY'))";
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                // PreparedStatement kullanarak sorguyu çalıştırma
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, rollno);
                pst.setString(2, name);
                pst.setString(3, fname);
                pst.setString(4,phone);
                pst.setString(5, email);
                pst.setString(6, adres);
                pst.setString(7, sifre);
                pst.setString(8, dob);


                // Veritabanına veri ekleme
                int result = pst.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Öğrenci Başarıyla Kaydedildi");
                    setVisible(false);  // Ekranı kapatma (veya başka bir işlem yapılabilir)
                } else {
                    JOptionPane.showMessageDialog(null, "Öğrenci Kaydedilemedi", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                c.closeConnection();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Öğrenci Kaydında Hata Oluştu: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }
    public static String convertTurkishDateToEnglish(String turkishDate) {
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("Oca", "Jan");
        monthMap.put("Şub", "Feb");
        monthMap.put("Mar", "Mar");
        monthMap.put("Nis", "Apr");
        monthMap.put("May", "May");
        monthMap.put("Haz", "Jun");
        monthMap.put("Tem", "Jul");
        monthMap.put("Ağu", "Aug");
        monthMap.put("Eyl", "Sep");
        monthMap.put("Eki", "Oct");
        monthMap.put("Kas", "Nov");
        monthMap.put("Ara", "Dec");

        for (Map.Entry<String, String> entry : monthMap.entrySet()) {
            turkishDate = turkishDate.replace(entry.getKey(), entry.getValue());
        }
        return turkishDate;
    }
    public static void main(String[] args) {
        new AddStudent();
    }
}
