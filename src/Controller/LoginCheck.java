package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LoginCheck {
    static DatabaseHandler conn = new DatabaseHandler();

    public static boolean Check (String phoneNum, String pass) {
        String query = "SELECT id, phone, password FROM customer WHERE phone = ? AND password = ?";
        boolean berhasil = false;

        try {
            conn.connect();
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, phoneNum);
            stmt.setString(2, pass);

            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                int id = rs.getInt("id");

                if(phoneNum.equals(phone) && pass.equalsIgnoreCase(password)) {
                    LoginSingleton.getInstance().setID(id);
                    berhasil = true;
                    break;
                } else {
                    berhasil = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            conn.disconnect();
        }
        return berhasil;
    }
}
