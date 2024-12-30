/*package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ogrenciEkran extends JFrame implements ActionListener {

    Choice crollno;
    JTextField tckimlik;
    JTable table;
    JButton search, print, update, add, cancel;

    ogrenciEkran() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("TC Kimlik Numarası");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        tckimlik = new JTextField();
        tckimlik.setBounds(180, 20, 150, 20);
        add(tckimlik);
        String username = Login.currentUsername;


        String query = "SELECT * FROM Ogrenciler WHERE tckimlik = ?";

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            // PreparedStatement kullanarak parametreli sorgu hazırlanır
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);  // Parametreyi (username) sorguya yerleştir

            // Sorgu çalıştırılır
            ResultSet rs = pstmt.executeQuery();

            // Sonuçları işle
            if (rs.next()) {
                // Öğrencinin bütün bilgilerini almak
                String tckimlik = rs.getString("tckimlik");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String dogumTarihi = rs.getString("telno");
                String adres = rs.getString("email");
                String telefon = rs.getString("dob");
                String email = rs.getString("adres");


                // Verileri kullanabilirsiniz. Örneğin:
                System.out.println("TC Kimlik: " + tckimlik);
                System.out.println("Ad: " + ad);
                System.out.println("Soyad: " + soyad);
                System.out.println("Doğum Tarihi: " + dogumTarihi);
                System.out.println("Adres: " + adres);
                System.out.println("Telefon: " + telefon);
                System.out.println("Email: " + email);

            } else {
                System.out.println("Öğrenci bulunamadı.");
            }

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
        new ogrenciEkran();
    }

}*/
/*package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ogrenciEkran extends JFrame implements ActionListener {

    JTextField tckimlik;
    JTable table;
    JButton cancel;

    ogrenciEkran() {
        // Pencere tasarımı
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Öğrenci Bilgileri");
        heading.setBounds(20, 20, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        // Tabloyu oluştur
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 80, 850, 500);
        add(sp);

        cancel = new JButton("İptal");
        cancel.setBounds(400, 600, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Veritabanından verileri çek
        loadStudentData(Login.currentUsername);

        setSize(450, 500);
        setLocationRelativeTo(null); // Ortalayarak başlat
        setLocation(getWidth() / 2, getHeight() / 2); // Ekranın dörtte birine yerleşim
        setVisible(true);
    }

    private void loadStudentData(String username) {
        // Giriş yapan kullanıcıya ait bilgileri çek
        String query = "SELECT * FROM Ogrenciler WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,  // Kaydırılabilir ResultSet
                    ResultSet.CONCUR_READ_ONLY          // Sadece okuma modunda
            );
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();  // Kaydırılabilir olduğundan, başa dönebilirsiniz
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı bilgileri bulunamadı!");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false); // Pencereyi kapat
        }
    }

    public static void main(String[] args) {
        new ogrenciEkran();
    }
}*/
/*package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ogrenciEkran extends JFrame implements ActionListener {

    JTable tableOgrenciler, tableIzinler;
    JButton cancel;

    ogrenciEkran() {
        // Pencere tasarımı
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel headingOgrenciler = new JLabel("Öğrenci Bilgileri");
        headingOgrenciler.setBounds(20, 20, 200, 30);
        headingOgrenciler.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(headingOgrenciler);

        // Öğrenciler Tablosu
        tableOgrenciler = new JTable();
        JScrollPane spOgrenciler = new JScrollPane(tableOgrenciler);
        spOgrenciler.setBounds(20, 60, 600, 150); // Öğrenci tablosu boyutları
        add(spOgrenciler);

        JLabel headingIzinler = new JLabel("İzinler");
        headingIzinler.setBounds(20, 230, 400, 30);
        headingIzinler.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(headingIzinler);

        // İzinler Tablosu
        tableIzinler = new JTable();
        JScrollPane spIzinler = new JScrollPane(tableIzinler);
        spIzinler.setBounds(20, 270, 500, 150); // İzinler tablosu boyutları
        add(spIzinler);

        // İptal Butonu
        cancel = new JButton("İptal");
        cancel.setBounds(150, 450, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Veritabanından verileri çek
        loadStudentData(Login.currentUsername);
        loadIzinData(Login.currentUsername);

        // Pencere boyutu ve yerleşimi
        setSize(700, 550);
        setLocationRelativeTo(null); // Ortalayarak başlat
        setVisible(true);
    }

    private void loadStudentData(String username) {
        // Öğrenci bilgilerini çek
        String query = "SELECT * FROM ogrenciler WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,  // Kaydırılabilir ResultSet
                    ResultSet.CONCUR_READ_ONLY          // Sadece okuma modunda
            );
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();  // Kaydırılabilir olduğundan, başa dönebilirsiniz
                tableOgrenciler.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı bilgileri bulunamadı!");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
    }

    private void loadIzinData(String username) {
        // Öğrencinin izin bilgilerini çek
        String query = "SELECT * FROM izinler_ogrenci WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,  // Kaydırılabilir ResultSet
                    ResultSet.CONCUR_READ_ONLY          // Sadece okuma modunda
            );
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();  // Kaydırılabilir olduğundan, başa dönebilirsiniz
                tableIzinler.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "İzin bilgileri bulunamadı!");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false); // Pencereyi kapat
        }
    }

    public static void main(String[] args) {
        new ogrenciEkran();
    }
}*/

/*package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ogrenciEkran extends JFrame implements ActionListener {

    JTable tableOgrenciler, tableIzinler, tableDisiplin;
    JButton cancel;

    ogrenciEkran() {
        // Pencere tasarımı
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel headingOgrenciler = new JLabel("Öğrenci Bilgileri");
        headingOgrenciler.setBounds(20, 20, 200, 30);
        headingOgrenciler.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(headingOgrenciler);

        // Öğrenciler Tablosu
        tableOgrenciler = new JTable();
        JScrollPane spOgrenciler = new JScrollPane(tableOgrenciler);
        spOgrenciler.setBounds(20, 60, 600, 150); // Öğrenci tablosu boyutları
        add(spOgrenciler);

        JLabel headingIzinler = new JLabel("İzinler");
        headingIzinler.setBounds(20, 230, 200, 30);
        headingIzinler.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(headingIzinler);

        // İzinler Tablosu
        tableIzinler = new JTable();
        JScrollPane spIzinler = new JScrollPane(tableIzinler);
        spIzinler.setBounds(20, 270, 600, 150); // İzinler tablosu boyutları
        add(spIzinler);

        JLabel headingDisiplin = new JLabel("Disiplin Kayıtları");
        headingDisiplin.setBounds(20, 440, 200, 30);
        headingDisiplin.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(headingDisiplin);

        // Disiplin Tablosu
        tableDisiplin = new JTable();
        JScrollPane spDisiplin = new JScrollPane(tableDisiplin);
        spDisiplin.setBounds(20, 480, 600, 150); // Disiplin tablosu boyutları
        add(spDisiplin);

        // İptal Butonu
        cancel = new JButton("İptal");
        cancel.setBounds(150, 650, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Veritabanından verileri çek
        loadStudentData(Login.currentUsername);
        loadIzinData(Login.currentUsername);
        loadDisiplinData(Login.currentUsername);

        // Pencere boyutu ve yerleşimi
        setSize(700, 750);
        setLocationRelativeTo(null); // Ortalayarak başlat
        setVisible(true);
    }

    private void loadStudentData(String username) {
        // Öğrenci bilgilerini çek
        String query = "SELECT * FROM ogrenciler WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();
                tableOgrenciler.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "Kullanıcı bilgileri bulunamadı!");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
    }

    private void loadIzinData(String username) {
        // Öğrencinin izin bilgilerini çek
        String query = "SELECT * FROM izinler_ogrenci WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();
                tableIzinler.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "İzin bilgileri bulunamadı!");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
    }

    private void loadDisiplinData(String username) {
        // Öğrencinin disiplin bilgilerini çek
        String query = "SELECT * FROM suclar WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.beforeFirst();
                tableDisiplin.setModel(DbUtils.resultSetToTableModel(rs));
            } else {
                JOptionPane.showMessageDialog(this, "Disiplin kayıtları bulunamadı!");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false); // Pencereyi kapat
        }
    }

    public static void main(String[] args) {
        new ogrenciEkran();
    }
}*/
/*package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ogrenciEkran extends JFrame implements ActionListener {

    JLabel iconOgrenciler, iconIzinler, iconDisiplin;
    JButton cancel;

    ogrenciEkran() {
        // Pencere tasarımı
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Öğrenci Ekranı");
        heading.setBounds(20, 20, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        // Öğrenci Bilgileri Resmi
        iconOgrenciler = new JLabel(new ImageIcon("icons/ogrencibilgi.jpg"));
        iconOgrenciler.setBounds(50, 100, 100, 100);
        add(iconOgrenciler);

        JLabel lblOgrenciler = new JLabel("Öğrenci Bilgileri");
        lblOgrenciler.setBounds(50, 210, 150, 20);
        lblOgrenciler.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblOgrenciler);

        // İzinler Resmi
        iconIzinler = new JLabel(new ImageIcon("icons/ogrenciizin.jpg"));
        iconIzinler.setBounds(250, 100, 100, 100);
        add(iconIzinler);

        JLabel lblIzinler = new JLabel("İzin Bilgileri");
        lblIzinler.setBounds(250, 210, 150, 20);
        lblIzinler.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblIzinler);

        // Disiplin Kayıtları Resmi
        iconDisiplin = new JLabel(new ImageIcon("icons/ogrencidisiplin.jpg"));
        iconDisiplin.setBounds(450, 100, 100, 100);
        add(iconDisiplin);

        JLabel lblDisiplin = new JLabel("Disiplin Kayıtları");
        lblDisiplin.setBounds(450, 210, 150, 20);
        lblDisiplin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDisiplin);

        // İptal Butonu
        cancel = new JButton("İptal");
        cancel.setBounds(250, 300, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Mouse Listeners
        iconOgrenciler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openStudentData();
            }
        });

        iconIzinler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openIzinData();
            }
        });

        iconDisiplin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openDisiplinData();
            }
        });

        // Pencere boyutu ve yerleşimi
        setSize(650, 400);
        setLocationRelativeTo(null); // Ortalayarak başlat
        setVisible(true);
    }

    private void openStudentData() {
        JFrame frame = new JFrame("Öğrenci Bilgileri");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM ogrenciler WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openIzinData() {
        JFrame frame = new JFrame("İzin Bilgileri");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM izinler_ogrenci WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openDisiplinData() {
        JFrame frame = new JFrame("Disiplin Kayıtları");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM suclar WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ogrenciEkran();
    }
}*/
package YurtSistemi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;


public class ogrenciEkran extends JFrame implements ActionListener {

    JButton btnOgrenciler, btnIzinler, btnDisiplin,btnOdeme;
    JLabel lblWeather;
    JButton btnWeather;
    JButton cancel;

    ogrenciEkran() {
        // Pencere tasarımı
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ÖĞRENCİ BİLGİ EKRANI");
        heading.setBounds(250, 50, 500, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

       // JLabel testLabel = new JLabel(new ImageIcon("izin.png"));
        //add(testLabel);

        // Öğrenci Bilgileri Butonu
        //btnOgrenciler = new JButton(new ImageIcon("icons/first.jpg"));
        btnOgrenciler = new JButton(new ImageIcon(getClass().getClassLoader().getResource("iconss/ogrencibilgi.png")));
        btnOgrenciler.setBounds(55, 150, 250, 250);
        btnOgrenciler.setBorderPainted(false);
        btnOgrenciler.setContentAreaFilled(false);
        btnOgrenciler.setFocusable(false);
        btnOgrenciler.addActionListener(this);
        add(btnOgrenciler);

        JLabel lblOgrenciler = new JLabel("Öğrenci Bilgileri");
        lblOgrenciler.setBounds(105, 400, 200, 25);
        lblOgrenciler.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblOgrenciler);

        // İzinler Butonu
        //btnIzinler = new JButton(new ImageIcon("src/izin.jpg"));
       btnIzinler = new JButton(new ImageIcon(getClass().getClassLoader().getResource("iconss/ögrencizinn.jpg")));

        btnIzinler.setBounds(20, 500, 300, 300);
        btnIzinler.setBorderPainted(false);
        btnIzinler.setContentAreaFilled(false);
        btnIzinler.setFocusable(false);
        btnIzinler.addActionListener(this);
        add(btnIzinler);

        JLabel lblIzinler = new JLabel("İzin Bilgileri");
        lblIzinler.setBounds(120, 820, 150, 25);
        lblIzinler.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblIzinler);

        // Disiplin Kayıtları Butonu
       // btnDisiplin = new JButton(new ImageIcon("disiplin.png"));
        btnDisiplin = new JButton(new ImageIcon(getClass().getClassLoader().getResource("iconss/ogrencidisiplin11.jpg")));
        btnDisiplin.setBounds(340, 70, 400, 400);
        btnDisiplin.setBorderPainted(false);
        btnDisiplin.setContentAreaFilled(false);
        btnDisiplin.setFocusable(false);
        btnDisiplin.addActionListener(this);
        add(btnDisiplin);

        JLabel lblDisiplin = new JLabel("Disiplin Kayıtları");
        lblDisiplin.setBounds(465, 400, 200, 25);
        lblDisiplin.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblDisiplin);

        btnOdeme = new JButton(new ImageIcon(getClass().getClassLoader().getResource("iconss/odeme.jpg")));
        btnOdeme.setBounds(300, 400, 500, 500);
        btnOdeme.setBorderPainted(false);
        btnOdeme.setContentAreaFilled(false);
        btnOdeme.setFocusable(false);
        btnOdeme.addActionListener(this);
        add( btnOdeme);

        JLabel lblOdeme = new JLabel("Odeme İşlemleri");
        lblOdeme.setBounds(465, 820, 200, 25);
        lblOdeme.setFont(new Font("Tahoma", Font.BOLD, 20));
        add( lblOdeme);

      /*
        // Hava Durumu Butonu
        btnWeather = new JButton(new ImageIcon(getClass().getClassLoader().getResource("iconss/cikis.jpg")));
        btnWeather.setBounds(750, 150, 100, 100);
        btnWeather.setBorderPainted(false);
        btnWeather.setContentAreaFilled(false);
        btnWeather.setFocusable(false);
        btnWeather.addActionListener(this);
        add(btnWeather);

        JLabel lblWeatherTitle = new JLabel("Hava Durumu");
        lblWeatherTitle.setBounds(800, 400, 200, 25);
        lblWeatherTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblWeatherTitle);

        lblWeather = new JLabel("Bilgi bekleniyor...");
        lblWeather.setBounds(750, 450, 300, 25);
        lblWeather.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblWeather);*/

        JLabel lblWeatherTitle = new JLabel("Hava Durumu");
        lblWeatherTitle.setBounds(800, 400, 200, 25);
        lblWeatherTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblWeatherTitle);
        updateWeatherInfo();



        cancel = new JButton(new ImageIcon(getClass().getClassLoader().getResource("iconss/cikis.jpg")));
        cancel.setBounds(750, 520, 250, 250);
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setFocusable(false);
        cancel.addActionListener(this);
        add( cancel);

        JLabel lblcikis = new JLabel("ÇIKIŞ");
        lblcikis.setBounds(800, 820, 200, 25);
        lblcikis.setFont(new Font("Tahoma", Font.BOLD, 20));
        add( lblcikis);


        // İptal Butonu
        //cancel = new JButton("Çıkış");
        //cancel.setBounds(370, 900, 100, 30);
        //cancel.addActionListener(this);
       // add(cancel);

        // Pencere boyutu ve yerleşimi
        setSize(1200, 980);
        setLocationRelativeTo(null); // Ortalayarak başlat
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Resme tıklama ile hangi veri gösterileceği
        if (ae.getSource() == btnOgrenciler) {
            openStudentData();
        } else if (ae.getSource() == btnIzinler) {
            openIzinData();
        } else if (ae.getSource() == btnDisiplin) {
            openDisiplinData();
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
        else if (ae.getSource() == btnOdeme) {
            openOdemeData();
        }


    }

    private void openStudentData() {
        JFrame frame = new JFrame("Öğrenci Bilgileri");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM ogrenciler WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openIzinData() {
        JFrame frame = new JFrame("İzin Bilgileri");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM izinler_ogrenci WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openDisiplinData() {
        JFrame frame = new JFrame("Disiplin Kayıtları");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM suclar WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openOdemeData() {
        JFrame frame = new JFrame("Odeme Kayıtları");
        JTable table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 600, 200);
        frame.add(sp);

        // Veritabanından veri çekme
        String query = "SELECT * FROM odemeler WHERE tckimlik = ?";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Login.currentUsername);

            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setSize(650, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Hava durumu butonuna tıklandığında çalışacak method
    private void updateWeatherInfo() {
        try {
            String apiKey = "cc6b8397534f67329a231ebacb218416"; // API anahtarınızı buraya yazın
            String city = "Ankara";
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric&lang=tr";

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                // JSON Parse
                JSONObject json = new JSONObject(result.toString());
                String cityName = json.getString("name");
                JSONObject main = json.getJSONObject("main");
                double temp = main.getDouble("temp");
                double feelsLike = main.getDouble("feels_like");
                int humidity = main.getInt("humidity");
                String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
                double windSpeed = json.getJSONObject("wind").getDouble("speed");

                // Ekrana bilgi gösterimi
                String weatherInfo = "<html>" +
                        "<table border='1' cellpadding='10' cellspacing='0'>" +
                        "<tr><td><b>Şehir</b></td><td>" + cityName + "</td></tr>" +
                        "<tr><td><b>Sıcaklık</b></td><td>" + temp + "°C</td></tr>" +
                        "<tr><td><b>Hissedilen Sıcaklık</b></td><td>" + feelsLike + "°C</td></tr>" +
                        "<tr><td><b>Açıklama</b></td><td>" + description + "</td></tr>" +
                        "<tr><td><b>Nem</b></td><td>%" + humidity + "</td></tr>" +
                        "<tr><td><b>Rüzgar Hızı</b></td><td>" + windSpeed + " m/s</td></tr>" +
                        "</table>" +
                        "</html>";

                JLabel lblWeather = new JLabel(weatherInfo);
                lblWeather.setBounds(750, 70, 400, 400);
                lblWeather.setFont(new Font("Tahoma", Font.PLAIN, 16));
                add(lblWeather);
                revalidate();
                repaint();

            } else {
                System.out.println("Hata kodu: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ogrenciEkran();
    }
}





