package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RegisterController {
    static DatabaseHandler conn = new DatabaseHandler();

    public static boolean addUser (String name, String password, String address, String phoneNum) {
        if (name.isEmpty() || password.isEmpty() || address.isEmpty() || phoneNum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus terisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        
        String query = "INSERT INTO customer (password, name, address, phone) VALUES (?, ?, ?, ?)";

        try {
        conn.connect();
        PreparedStatement statement = conn.con.prepareStatement(query);
        statement.setString(1, password);
        statement.setString(2, name);
        statement.setString(3, address);
        statement.setString(4, phoneNum);

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public static boolean CheckPhoneAddress (String phoneNum, String address) {
        String query = "SELECT phone, address FROM customer WHERE phone = ? AND address = ?";
        boolean kosong = false;

        try {
            conn.connect();
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, phoneNum);
            stmt.setString(2, address);

            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                kosong = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data sudah terdaftar", "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            conn.disconnect();
        }
        return kosong;
        
    }
}
