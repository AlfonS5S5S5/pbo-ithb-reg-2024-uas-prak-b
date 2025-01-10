package Controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TransactionController {
    private static DatabaseHandler conn = new DatabaseHandler();

    public static boolean tambahTransaction(String deliveryType, int weight, Date createdAt, String name, String address, String phone) {
        int cust_id = LoginSingleton.getInstance().getID();

        Timestamp timestamp = new Timestamp(createdAt.getTime());

        int deliveryCategory = getCategory(deliveryType);
        int totalCost = calculateCost(deliveryCategory, weight);

        if (totalCost == -1) {
            JOptionPane.showMessageDialog(null, "Gagal menghitung biaya", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String query = "INSERT INTO transaction (customer_id, delivery_type, expected_weight, total_cost, created_at, receipt_name, receipt_address, receipt_phone) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.con.prepareStatement(query)) {
            conn.connect();
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
            conn.disconnect();
        }
    }

    public ArrayList<String> getDeliveryTypes() {
        ArrayList<String> deliveryTypes = new ArrayList<>();
        String query = "SELECT category_type FROM category";

        try (Statement stmt = conn.con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            conn.connect();
            while (rs.next()) {
                deliveryTypes.add(rs.getString("category_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return deliveryTypes;
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

    public static int getFee (int category_id) {
        int fee = -1;
        String query = "SELECT data_fee FROM category WHERE category_id = ?";

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            conn.connect();
            stmt.setInt(1, category_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fee = rs.getInt("data_fee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return fee;
    }

	public void tambahTransaction(String typeDelivery, int berat, java.util.Date date, String nama, String alamat,
			String nomor) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'tambahTransaction'");
	}
}
