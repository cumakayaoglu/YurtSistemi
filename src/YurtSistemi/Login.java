/*package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton login, cancel;
    JTextField tfUsername, tfPassword;
    static String currentUsername;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lbUsername = new JLabel("Kullanıcı Adı");
        lbUsername.setBounds(40, 20, 100, 20);
        add(lbUsername);

        JLabel lbPassword = new JLabel("Şifre");
        lbPassword.setBounds(80, 50, 100, 20);
        add(lbPassword);

        tfUsername = new JTextField();
        tfUsername.setBounds(150, 20, 150, 20);
        add(tfUsername);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(150, 50, 150, 20);
        add(tfPassword);

        login = new JButton("Giriş Yap");
        login.setBounds(150, 80, 150, 25);
        login.setBackground(Color.GRAY);
        login.setForeground(Color.white);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(login);

        cancel = new JButton("Çıkış");
        cancel.setBounds(150, 110, 150, 25);
        cancel.setBackground(Color.GRAY);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
             currentUsername = tfUsername.getText();
            String password = tfPassword.getText(); // Şifreyi alıyoruz

            // Kullanıcı doğrulama sorgusu
            String query = "SELECT * FROM login WHERE username = ? AND password = ?";

            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                // PreparedStatement kullanarak SQL enjeksiyonunu önlüyoruz
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, currentUsername);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String userRole = rs.getString("role"); // Kullanıcının rolü (ogrenci / memur)

                    if (userRole.equals("ogrenci")) {
                        // Öğrenci için ekran
                        setVisible(false); // Login ekranını kapat
                        //new Project("ogrenci"); // Öğrenci için Project sınıfını aç
                        new ogrenciEkran();
                    } else if (userRole.equals("memur")) {
                        // Memur için ekran
                        setVisible(false); // Login ekranını kapat
                        new Project("memur"); // Memur için Project sınıfını aç
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre!");
                }

                rs.close();
                pstmt.close();
                c.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}*/

package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton login, cancel;
    JTextField tfUsername;
    JPasswordField tfPassword;
    static String currentUsername;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lbUsername = new JLabel("Kullanıcı Adı");
        lbUsername.setBounds(40, 20, 100, 20);
        add(lbUsername);

        JLabel lbPassword = new JLabel("Şifre");
        lbPassword.setBounds(80, 50, 100, 20);
        add(lbPassword);

        tfUsername = new JTextField();
        tfUsername.setBounds(150, 20, 150, 20);
        add(tfUsername);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(150, 50, 150, 20);
        add(tfPassword);

        login = new JButton("Giriş Yap");
        login.setBounds(150, 80, 150, 25);
        login.setBackground(Color.GRAY);
        login.setForeground(Color.white);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(login);

        cancel = new JButton("Çıkış");
        cancel.setBounds(150, 110, 150, 25);
        cancel.setBackground(Color.GRAY);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            currentUsername = tfUsername.getText();
            String password = new String(tfPassword.getPassword());

            if (currentUsername.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kullanıcı adı ve şifre boş bırakılamaz!");
                return;
            }

            String query = "SELECT * FROM login WHERE username = ? AND password = ?";

            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, currentUsername);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String userRole = rs.getString("role");

                    if (userRole.equals("ogrenci")) {
                        setVisible(false);
                        new ogrenciEkran();
                    } else if (userRole.equals("memur")) {
                        setVisible(false);
                        new Project("memur");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre!");
                }

                rs.close();
                pstmt.close();
                c.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

