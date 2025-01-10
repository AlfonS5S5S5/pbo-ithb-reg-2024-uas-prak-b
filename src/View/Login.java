package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        labelIcon.setBounds(175, 20, 60, 60);
        frame.add(labelIcon);

        JLabel labelTitle = new JLabel("Pratama Plication");
        labelTitle.setBounds(175, 70, 130, 45);
        frame.add(labelTitle);

        JLabel labelTitleLogin = new JLabel("== Login ==");
        labelTitleLogin.setBounds(175, 20, 100, 35);
        frame.add(labelTitleLogin);

        JLabel labelPhoneNum = new JLabel("Phone Number:");
        labelPhoneNum.setBounds(50, 70, 100, 25);
        frame.add(labelPhoneNum);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 70, 200, 25);
        frame.add(phoneField);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(50, 110, 100, 25);
        frame.add(labelPassword);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 25);
        frame.add(passwordField);

        JButton submitButton = new JButton("Register");
        submitButton.setBounds(150, 160, 200, 25);
        frame.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 260, 200, 25);
        frame.add(backButton);
        
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String pass = String.valueOf(passwordField.getPassword());

                if (LoginCheck.Check(phone, pass)) {

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
