package YurtSistemi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class IzinCalisan extends JFrame implements ActionListener {
    JTextField calisanID;
    JButton submit, cancel;
    JDateChooser tarihBaslangic , tarihBitis;
    IzinCalisan(){
        setSize(500, 550);
        setLocation(550, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Çalışan İzin İşlemleri");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel lblrollno = new JLabel("Çalışan ID");
        lblrollno.setBounds(60, 100, 200, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        calisanID = new JTextField();
        calisanID.setBounds(60, 130, 200, 20);
        add(calisanID);

        JLabel lblBaslangic = new JLabel("İzin Başlangıç Tarihi");
        lblBaslangic.setBounds(60, 180, 200, 20);
        lblBaslangic.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblBaslangic);

        tarihBaslangic = new JDateChooser();
        tarihBaslangic.setBounds(60, 210, 200, 25);
        add(tarihBaslangic);

        JLabel lblBitis = new JLabel("İzin Bitiş Tarihi");
        lblBitis.setBounds(60, 260, 200, 20);
        lblBitis.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblBitis);

        tarihBitis = new JDateChooser();
        tarihBitis.setBounds(60, 290, 200, 25);
        add(tarihBitis);

        submit = new JButton("Onay");
        submit.setBounds(60, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("İptal");
        cancel.setBounds(200, 350, 100, 25);
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
            String calisanid = calisanID.getText();
            String dateBaslangic = ((JTextField) tarihBaslangic.getDateEditor().getUiComponent()).getText();
            String dateBitis = ((JTextField) tarihBitis.getDateEditor().getUiComponent()).getText();

            dateBaslangic = convertTurkishDateToEnglish(dateBaslangic);
            dateBitis = convertTurkishDateToEnglish(dateBitis);

            String query = "INSERT INTO izinler_calisan (calisanid, datebaslangic,datebitis) " + "VALUES (?,TO_DATE(?, 'DD Mon YYYY'),TO_DATE(?, 'DD Mon YYYY'))";

            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                // PreparedStatement kullanarak sorguyu çalıştırma
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, calisanid);
                pst.setString(2, dateBaslangic);
                pst.setString(3, dateBitis);

                // Veritabanına veri ekleme
                int result = pst.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Çalışan İzni Kaydedildi");
                    setVisible(false);  // Ekranı kapatma (veya başka bir işlem yapılabilir)
                } else {
                    JOptionPane.showMessageDialog(null, "Çalışan İzni Kaydedilemedi", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                c.closeConnection();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Çalışan İzni Eklenirken Hata Oluştu: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        new IzinCalisan();
    }
}