package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DisiplinIslem extends JFrame implements ActionListener {
    JButton cancel, submit;
    JTextField tfrollno ,tfmarks1,tfmarks2,tfmarks3,tfmarks4,tfmarks5; ;
    JComboBox suc1 , suc2,suc3,suc4,suc5;
    JLabel lblrollno;
    int x = 0;
    DisiplinIslem(){

        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("iconss/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(570, 40, 400, 300);
        add(image);

        JLabel heading = new JLabel("Öğrenci Disiplin İşlemleri");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        lblrollno = new JLabel("TC Kimlik Numarası Giriniz");
        lblrollno.setBounds(50, 70, 150, 20);
        add(lblrollno);

        tfrollno = new JTextField();
        tfrollno.setBounds(200, 70, 150, 20);
        add(tfrollno);

        String suclar[] = {"Yüz Kızartıcı", "Toplum Huzurunu Bozma", "Devlet Malına Zarar Verme", "Geç Kalma", "Sigara İçme"};
        suc1 = new JComboBox(suclar);
        suc1.setBounds(50, 200, 200, 20);
        suc1.setBackground(Color.WHITE);
        add(suc1);


        tfmarks1 = new JTextField();
        tfmarks1.setBounds(250, 200, 200, 20);
        add(tfmarks1);

        submit = new JButton("Onay");
        submit.setBounds(70, 360, 150, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Çıkış");
        cancel.setBounds(280, 360, 150, 25);
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
            String rollno = tfrollno.getText();
            String suc11 = (String) suc1.getSelectedItem();
            String derece1 = tfmarks1.getText();




            String query = "insert into suclar (tckimlik,suctipi,derece) VALUES (?,?,?)";
            // String dereceSorgusu = "insert into dereceler1 (rollno) values (?)";

            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                // PreparedStatement kullanarak sorguyu çalıştırma
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, rollno);
                pst.setString(2, suc11);
                pst.setString(3, derece1);
//                PreparedStatement pst2 = conn.prepareStatement(dereceSorgusu);
//                pst2.setString(1, rollno);



                // Veritabanına veri ekleme
                int result = pst.executeUpdate();
                // int result1 = pst2.executeUpdate();

                //result2 > 0 kontrolünü sagla
                if (result > 0 ) { //buradaki hata kısmını tekrar kontrol et
                    JOptionPane.showMessageDialog(null, "Disiplin İşlemi Kaydedildi");
                    setVisible(false);  // Ekranı kapatma (veya başka bir işlem yapılabilir)
                } else {
                    JOptionPane.showMessageDialog(null, "Disiplin İşlemi Kaydedilemedi", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                c.closeConnection();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Disiplin Kaydında Hata Oluştu: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new DisiplinIslem();
    }
}
