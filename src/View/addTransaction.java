package View;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.TransactionController;

public class addTransaction {

    private JFrame frame;
    private JTextField nameField, addressField, phoneField, weightField;
    private JButton saveButton, backButton;

    public addTransaction() {
        TransactionController transaction = new TransactionController();

        frame = new JFrame("Add Transaction");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 100, 25);
        frame.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 25);
        frame.add(nameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 70, 100, 25);
        frame.add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(150, 70, 200, 25);
        frame.add(addressField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 110, 100, 25);
        frame.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(150, 110, 200, 25);
        frame.add(phoneField);

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setBounds(50, 150, 100, 25);
        frame.add(weightLabel);
        weightField = new JTextField();
        weightField.setBounds(150, 150, 200, 25);
        frame.add(weightField);

        JLabel deliveryTypeLabel = new JLabel("Delivery Type:");
        deliveryTypeLabel.setBounds(50, 190, 100, 25);
        frame.add(deliveryTypeLabel);

        ArrayList<String> deliveryTypesList = transaction.getDeliveryTypes();

        String[] deliveryTypes = new String[deliveryTypesList.size()];
        deliveryTypes = deliveryTypesList.toArray(deliveryTypes);

        JComboBox<String> deliveryTypeCombo = new JComboBox<>(deliveryTypes);
        deliveryTypeCombo.setBounds(150, 190, 200, 25);
        frame.add(deliveryTypeCombo);

        // Buttons
        saveButton = new JButton("Save");
        saveButton.setBounds(50, 240, 140, 30);
        frame.add(saveButton);

        saveButton.addActionListener(e -> {
            Date date = new Date();
            String nama = nameField.getText();
            String alamat = addressField.getText();
            String nomor = phoneField.getText();
            int berat = Integer.parseInt(weightField.getText());
            String typeDelivery = (String) deliveryTypeCombo.getSelectedItem();

            if (nama.isEmpty() || alamat.isEmpty() || nomor.isEmpty() || berat == 0 || weightField.getText() == null || typeDelivery.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field harus di isi semua", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                transaction.tambahTransaction(typeDelivery, berat, date, nama, alamat, nomor);
                JOptionPane.showMessageDialog(null, "Anda berhasil menambahkan Transaksi");
            }

            frame.dispose();
            new MainMenu();
        });

        backButton = new JButton("Back");
        backButton.setBounds(210, 240, 140, 30);
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        frame.add(backButton);

        frame.setVisible(true);
    }
}
