package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Classes.DeliveryDetails;
import Models.Classes.Transaction;

public class HistoryDeliveryController {
    static DatabaseHandler conn = new DatabaseHandler();

    public static List<Transaction> getTransactions() {
        List<Transaction> transactionArr = new ArrayList<>();
        Map<Integer, DeliveryDetails> deliveryDetailsMap = new HashMap<>();
        Map<Integer, Transaction> transactionMap = new HashMap<>();
    
        String query = "SELECT t.id AS transaction_id, t.delivery_type, c.data_fee AS delivery_fee, t.total_cost, t.created_at " +
                                "FROM transaction t " +
                                "JOIN category c ON t.delivery_type = c.delivery_type " +
                                "ORDER BY t.created_at DESC";
    
        String deliveryDetailsQuery = "SELECT transaction_id, MAX(date) AS updated_at FROM delivery_details " +
                                      "GROUP BY transaction_id ORDER BY updated_at DESC";
    
        try {
            PreparedStatement stmtTransaksi = conn.con.prepareStatement(query);
            ResultSet rs = stmtTransaksi.executeQuery();
    
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setDelivery_type(rs.getString("delivery_type"));
                transaction.setDelivery_Fee(rs.getDouble("delivery_fee"));
                transaction.setTotal_cost(rs.getInt("total_cost"));
                transaction.setCreated_at(rs.getDate("created_at"));
    
                transactionMap.put(transaction.getId(), transaction);
                transactionArr.add(transaction);
            }
    
            PreparedStatement stmtDeliveryDetails = conn.con.prepareStatement(deliveryDetailsQuery);
            ResultSet rsDeliveryDetails = stmtDeliveryDetails.executeQuery();
    
            while (rsDeliveryDetails.next()) {
                int transactionId = rsDeliveryDetails.getInt("transaction_id");
                DeliveryDetails deliveryDetails = new DeliveryDetails();
                deliveryDetails.setId(transactionId);
                deliveryDetails.setDate(rsDeliveryDetails.getDate("updated_at"));
    
                deliveryDetailsMap.put(transactionId, deliveryDetails);
            }

            for (Transaction transaction : transactionArr) {
                DeliveryDetails deliveryDetails = deliveryDetailsMap.get(transaction.getId());
                if (deliveryDetails != null) {
                    transaction.setUpdated_at(deliveryDetails.getDate());
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return transactionArr;
    }
    
}
