package View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.TransactionDetailController;
import Models.Classes.Transaction;

public class ViewTransactionDetail {

    private JFrame frame;

    public ViewTransactionDetail(int transactionId) {
        initialize(transactionId);
    }

    private void initialize(int transactionId) {
        frame = new JFrame("Detail Transaksi");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JTextArea transactionDetailsArea = new JTextArea();
        transactionDetailsArea.setEditable(false);

        Transaction transaction = TransactionDetailController.getTransactionDetail(transactionId);
        if (transaction != null) {
            transactionDetailsArea.setText("Transaction ID: " + transaction.getId() + "\n"
                    + "Delivery Type: " + transaction.getDelivery_type() + "\n"
                    + "Total Cost: " + transaction.getTotal_cost() + "\n"
                    + "Created At: " + transaction.getCreated_at() + "\n"
                    + "Updated At: " + transaction.getUpdated_at());
        } else {
            transactionDetailsArea.setText("Transaction details not found.");
        }

        JScrollPane scrollPane = new JScrollPane(transactionDetailsArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.dispose());
        frame.getContentPane().add(backButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
