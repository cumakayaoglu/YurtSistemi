package YurtSistemi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conn {
    public PreparedStatement s;
    private Connection conn;

    // Constructor ile bağlantıyı başlatıyoruz
    public Conn() {
        String url = "jdbc:postgresql://localhost:5432/YurtSistemi";
        String user = "postgres";
        String password = "5478963210cuma";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Bağlantı başarılı!");
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
    }

    // Bağlantı nesnesini döndüren bir getter metot
    public Connection getConnection() {
        return conn;
    }

    // Bağlantıyı kapatma metodu
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Bağlantı kapatıldı.");
            }
        } catch (SQLException e) {
            System.out.println("Bağlantı kapatılamadı: " + e.getMessage());
        }
    }
}