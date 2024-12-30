package YurtSistemi;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class DisiplinKayit extends JFrame implements ActionListener {
    JTextField tfrollno;
    JButton cancel , search;
    JLabel sub1,sub2,sub3,sub4,sub5;
    DisiplinKayit(){


        setSize(1000, 850);
        setLocation(500, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("YURT DISIPLIN ISLEMLERI");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Öğrenci Disiplin Kayıtları");
        subheading.setBounds(100, 150, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel lblrollno = new JLabel("TC Kimlik Numarası");
        lblrollno.setBounds(60, 100, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblrollno);

        tfrollno = new JTextField();
        tfrollno.setBounds(200,100,100,25);
        add(tfrollno);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);

        sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);

        sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);

        sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);

        sub5 = new JLabel();
        sub5.setBounds(100, 320, 500, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub5);
        setVisible(true);

        search = new JButton("Ara");
        search.setBounds(320, 100, 80, 20);
        search.addActionListener(this);
        add(search);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String rollno = tfrollno.getText();
        String query1 = "select * from suclar where tckimlik = '" + rollno + "'";
        String query2 = "select * from suclar where tckimlik = '" + rollno + "'";

        if (ae.getSource() == search) {
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement();

                ResultSet rs1 = stmt1.executeQuery(query1);

                // Temizleme
                sub1.setText("");
                sub2.setText("");
                sub3.setText("");
                sub4.setText("");
                sub5.setText("");

                int counter = 0; // Hangi etiketi doldurduğumuzu takip eder
                while (rs1.next() && counter < 5) {
                    String suctipi = rs1.getString("suctipi");

                    switch (counter) {
                        case 0:
                            sub1.setText("Suç Tipi: " + suctipi);
                            break;
                        case 1:
                            sub2.setText("Suç Tipi: " + suctipi);
                            break;
                        case 2:
                            sub3.setText("Suç Tipi: " + suctipi);
                            break;
                        case 3:
                            sub4.setText("Suç Tipi: " + suctipi);
                            break;
                        case 4:
                            sub5.setText("Suç Tipi: " + suctipi);
                            break;
                    }
                    counter++;
                }

                // İkinci sorgu işlemi
                ResultSet rs2 = stmt2.executeQuery(query2);
                counter = 0; // İkinci sorgu için sayacı sıfırla
                while (rs2.next() && counter < 5) {
                    String derece = rs2.getString("derece");

                    switch (counter) {
                        case 0:
                            sub1.setText(sub1.getText() + " - Derece: " + derece);
                            break;
                        case 1:
                            sub2.setText(sub2.getText() + " - Derece: " + derece);
                            break;
                        case 2:
                            sub3.setText(sub3.getText() + " - Derece: " + derece);
                            break;
                        case 3:
                            sub4.setText(sub4.getText() + " - Derece: " + derece);
                            break;
                        case 4:
                            sub5.setText(sub5.getText() + " - Derece: " + derece);
                            break;
                    }
                    counter++;
                }

                rs1.close();
                rs2.close();
                stmt1.close();
                stmt2.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new DisiplinKayit();
    }
}
