package YurtSistemi;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class fatura extends JFrame {

    fatura() {
        setSize(1000, 700);
        setLocation(250, 50);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Fatura");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        JTable table = new JTable();

        String query = "SELECT * FROM faturalar";
        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.next();
            //while(rs.next()) {
            //    crollno.add(rs.getString("rollno"));
            //  }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 60, 1000, 700);
        add(jsp);

        setVisible(true);



    }

    public static void main(String[] args) {
        new fatura();
    }
}