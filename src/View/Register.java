package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.RegisterController;

public class Register {
    private JFrame frame;

    public Register() {
        inputRegister();
    }

    public void inputRegister() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 550);
        frame.setTitle("Register");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelTitleLogin = new JLabel("== Register ==");
        labelTitleLogin.setBounds(175, 20, 100, 35);
        frame.add(labelTitleLogin);

        JLabel labelUsername = new JLabel("Name:");
        labelUsername.setBounds(50, 70, 100, 25);
        frame.add(labelUsername);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 70, 200, 25);
        frame.add(nameField);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(50, 110, 100, 25);
        frame.add(labelPassword);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 25);
        frame.add(passwordField);

        JLabel labelAddress = new JLabel("Address:");
        labelAddress.setBounds(50, 150, 100, 25);
        frame.add(labelAddress);

        JTextField addressField = new JTextField();
        addressField.setBounds(150, 150, 200, 25);
        frame.add(addressField);

        JLabel labelPhoneNum = new JLabel("Phone Number:");
        labelPhoneNum.setBounds(50, 190, 100, 25);
        frame.add(labelPhoneNum);

        JTextField phoneNumField = new JTextField();
        phoneNumField.setBounds(150, 190, 200, 25);
        frame.add(phoneNumField);

        JButton submitButton = new JButton("Register");
        submitButton.setBounds(150, 230, 200, 25);
        frame.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 260, 200, 25);
        frame.add(backButton);
        
        frame.setVisible(true);
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String address = addressField.getText();
                String phoneNum = phoneNumField.getText();

                boolean isSaved = RegisterController.addUser(name, password, address, phoneNum);

                if (isSaved) {
                    JOptionPane.showMessageDialog(null, "Selamat, Anda sudah selesai melakukan pendaftaran dan akan melanjutkan ke menu customer");
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Account Anda gagal didaftarkan!", "Coba lagi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenu();
            }
        });
    }
}
