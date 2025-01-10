package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.HistoryDeliveryController;
import Models.Classes.Transaction;

public class ViewHistoryDelivery {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JButton backButton;

    public ViewHistoryDelivery() {
        initialize();
        loadData();
    }

    private void initialize() {
        frame = new JFrame("History Pengiriman");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        model = new DefaultTableModel(
            new Object[]{"Transaction ID", "Delivery Type", "Delivery Fee", "Total Cost", "Created At", "Updated At", "Actions"}, 
            0
        );
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        frame.getContentPane().add(backButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadData() {
        List<Transaction> transactionData = HistoryDeliveryController.getTransactions();
        for (Transaction transaction : transactionData) {
            Object[] row = {
                transaction.getId(),
                transaction.getDelivery_type(),
                transaction.getDelivery_Fee(),
                transaction.getTotal_cost(),
                transaction.getCreated_at(),
                transaction.getUpdated_at(),
                createDetailButton(transaction)
            };
            model.addRow(row);
        }
    }

    private JButton createDetailButton(Transaction transaction) {
        JButton detailButton = new JButton("Lihat Detail");
        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewTransactionDetail(transaction.getId());
            }
        });
        return detailButton;
    }
    
}
