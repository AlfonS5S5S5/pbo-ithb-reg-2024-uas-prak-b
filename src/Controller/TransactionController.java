package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class TransactionController {
    private static DatabaseHandler conn = new DatabaseHandler();

    public boolean tambahTransaction(String deliveryType, int weight, Date createdAt, String name, String address, String phone) {
        int cust_id = LoginSingleton.getInstance().getID();
    
        Timestamp timestamp = new Timestamp(createdAt.getTime());
    
        int deliveryCategory = getCategory(deliveryType);
        int totalCost = calculateCost(deliveryCategory, weight);
    
        if (totalCost == -1) {
            JOptionPane.showMessageDialog(null, "Gagal menghitung biaya", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        String query = "INSERT INTO transaction (customer_id, delivery_type, expected_weight, total_cost, created_at, receipt_name, receipt_address, receipt_phone) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        PreparedStatement stmt = null;
    
        try {
            conn.connect();
    
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, cust_id);
            stmt.setInt(2, deliveryCategory);
            stmt.setInt(3, weight);
            stmt.setInt(4, totalCost);
            stmt.setTimestamp(5, timestamp);
            stmt.setString(6, name);
            stmt.setString(7, address);
            stmt.setString(8, phone);
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conn.disconnect();
        }
    }
    
    public String[] getDeliveryTypes() {
        ArrayList<String> deliveryTypes = new ArrayList<>();
        String query = "SELECT category_type FROM category";
        Statement stmt = null;
        ResultSet rs = null;
    
        try {
            conn.connect();
    
            stmt = conn.con.createStatement();
            rs = stmt.executeQuery(query);
    
            while (rs.next()) {
                deliveryTypes.add(rs.getString("category_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
    
        return deliveryTypes.toArray(new String[0]);
    }
    
    public static int getCategory(String deliveryType) {
        switch (deliveryType.toLowerCase()) {
            case "building materials":
                return 1;
            case "house moving":
                return 2;
            default:
                return 3;
        }
    }
    
    public static int calculateCost(int category, int weight) {
        if (weight <= 0) {
            return -1;
        }
    
        int fees = getFee(category);
    
        if (fees == -1) {
            return -1;
        }
        return (int) Math.ceil(weight * fees);
    }
    
    public static int getFee(int category_id) {
        int fee = -1;
        String query = "SELECT data_fee FROM category WHERE category_id = ?";
    
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            conn.connect();
    
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, category_id);
    
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                fee = rs.getInt("data_fee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
    
        return fee;
    }
    
}
