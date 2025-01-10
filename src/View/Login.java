package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.LoginCheck;

public class Login {
    private JFrame frame;

    public Login() {
        inputLogin();
    }

    public void inputLogin() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 550);
        frame.setTitle("Login");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        String imgPath = "C:\\Kuliah\\Semester 3\\Prak.PBO\\UAS\\logo.jpg";
        ImageIcon icon = new ImageIcon(imgPath);
    
        JLabel labelIcon = new JLabel(icon);
        labelIcon.setBounds(170, 20, 60, 60);
        frame.add(labelIcon);
    
        JLabel labelTitle = new JLabel("Pratama Plication");
        labelTitle.setBounds(150, 90, 150, 30);
        frame.add(labelTitle);
    
        JLabel labelTitleLogin = new JLabel("== Login ==");
        labelTitleLogin.setBounds(175, 120, 100, 35);
        frame.add(labelTitleLogin);
    
        JLabel labelPhoneNum = new JLabel("Phone Number:");
        labelPhoneNum.setBounds(50, 180, 100, 25);
        frame.add(labelPhoneNum);
    
        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 180, 200, 25);
        frame.add(phoneField);
    
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(50, 220, 100, 25);
        frame.add(labelPassword);
    
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 220, 200, 25);
        frame.add(passwordField);
    
        JButton submitButton = new JButton("Login");
        submitButton.setBounds(150, 260, 200, 35);
        frame.add(submitButton);
    
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 310, 200, 35);
        frame.add(backButton);
    
        frame.setVisible(true);
    
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String pass = String.valueOf(passwordField.getPassword());
    
                if (LoginCheck.Check(phone, pass)) {
                    JOptionPane.showMessageDialog(null, "Anda Berhasil Login");
                    frame.dispose();
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Anda Gagal Login");
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
