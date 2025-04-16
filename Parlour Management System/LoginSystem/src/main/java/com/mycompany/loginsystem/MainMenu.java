// MainMenu.java
package com.mycompany.loginsystem;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Top Parlour");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(10, 25, 49)); // Navy Blue

        JLabel logoLabel = new JLabel(new ImageIcon("logo.png"));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.setBackground(new Color(33, 150, 243));
        registerButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        registerButton.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(logoLabel, gbc);
        gbc.gridy = 1;
        add(loginButton, gbc);
        gbc.gridy = 2;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            new LoginWindow().setVisible(true);
            dispose(); // close MainMenu
        });

        registerButton.addActionListener(e -> {
            new RegisterWindow().setVisible(true);
            dispose(); // close MainMenu
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}