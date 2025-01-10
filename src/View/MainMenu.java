package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.LoginSingleton;

public class MainMenu {
   private JFrame frame;

    public MainMenu() {
        showMainMenu();
    }

    public void showMainMenu() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 800;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        Font titleFont = new Font("SansSerif", Font.BOLD, 22);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 18);

        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0x567af0));
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel title = new JLabel("Main Menu");
        title.setBounds(250, 30, 300, 30);
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        panel.add(title);

        JButton LoginButton = createButton("Login", 120, 100, buttonFont);
        LoginButton.addActionListener(e -> {
            frame.dispose();
            new Login();
        });
        panel.add(LoginButton);

        JButton RegisterButton = createButton("Register", 120, 160, buttonFont);
        RegisterButton.addActionListener(e -> {
            frame.dispose();
            new Register();
        });
        panel.add(RegisterButton);

        JButton addTransactionBtn = createButton("Menambahkan Transaksi Pengiriman", 120, 220, buttonFont);
        addTransactionBtn.addActionListener(e -> {
            if (LoginSingleton.getInstance().getID() == 0) {
                JOptionPane.showMessageDialog(null, "Belum Login", "Error",JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                new AddTransaction();
            }

        });
        panel.add(addTransactionBtn);

        JButton viewHistoryBtn = createButton("Lihat History Pengiriman", 120, 280, buttonFont);
        viewHistoryBtn.addActionListener(e -> {
            frame.dispose();
            new ViewHistoryDelivery();
        });
        panel.add(viewHistoryBtn);

        
        JButton addDetailsButton = createButton("Menambahkan Detail Transaksi", 120, 340, buttonFont);
        addDetailsButton.addActionListener(e -> {
            if (LoginSingleton.getInstance().getID() == 0) {
                JOptionPane.showMessageDialog(null, "Belum Login", "Error",JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                new AddDetail();
            }

        });
        panel.add(addDetailsButton);
        
        frame.add(panel);
        frame.setVisible(true);

    }
    private JButton createButton(String text, int x, int y, Font font) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 360, 50);
        button.setFont(font);
        button.setBackground(new Color(0x2d5aed));
        button.setForeground(Color.WHITE);
        return button;
    }
}
