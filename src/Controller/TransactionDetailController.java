package Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import Models.Enumm.TransactionStatus;

public class TransactionDetailController {
    static DatabaseHandler conn = new DatabaseHandler();

    public boolean addDetail (int transaction_id, TransactionStatus status, String posisi, String evidence, Date date, String updated_by) {
        Timestamp timestamp = new Timestamp(date.getTime());

        String query = "INSERT into delivery_details (transaction_id, status, current_position, evidence, date, updated_by) VALUES (?,?,?,?,?,?)";

        try {
            conn.connect();
            PreparedStatement stmt = conn.con.prepareStatement(query);

            stmt.setInt(1, transaction_id);
            stmt.setString(2, status.name());
            stmt.setString(3, posisi);
            stmt.setString(4, evidence);
            stmt.setTimestamp(5, timestamp);
            stmt.setString(6, updated_by);

            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Detail pengiriman berhasil ditambahkan.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menambahkan detail pengiriman.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menambahkan detail pengiriman: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            conn.disconnect();
        }
    }
}
