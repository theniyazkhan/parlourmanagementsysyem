// LoginWindow.java
package com.mycompany.loginsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JRadioButton userRadioButton, adminRadioButton;
    private ButtonGroup roleGroup;

    public LoginWindow() {
        setTitle("Login");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(10, 25, 49)); // Navy Blue

        JLabel logoLabel = new JLabel(new ImageIcon("logo.png"));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        userRadioButton = new JRadioButton("User");
        adminRadioButton = new JRadioButton("Admin");
        userRadioButton.setBackground(new Color(10, 25, 49));
        adminRadioButton.setBackground(new Color(10, 25, 49));
        userRadioButton.setForeground(Color.WHITE);
        adminRadioButton.setForeground(Color.WHITE);

        roleGroup = new ButtonGroup();
        roleGroup.add(userRadioButton);
        roleGroup.add(adminRadioButton);

        loginButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(33, 150, 243));
        backButton.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(logoLabel, gbc);

        gbc.gridy++; gbc.gridwidth = 1;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(userRadioButton, gbc);
        gbc.gridx = 1;
        add(adminRadioButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(backButton, gbc);
        gbc.gridx = 1;
        add(loginButton, gbc);

        loginButton.addActionListener(e -> login());
        backButton.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (adminRadioButton.isSelected()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("admins.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {
                        new AdminWindow().setVisible(true);
                        dispose();
                        return;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading admin file.");
            }
            JOptionPane.showMessageDialog(this, "Invalid admin credentials.");
        } else if (userRadioButton.isSelected()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 6 && data[3].equals(username) && data[4].equals(password)) {
                        new UserDashboard(username).setVisible(true);
                        dispose();
                        return;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading user file.");
            }
            JOptionPane.showMessageDialog(this, "Invalid user credentials.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a role (User/Admin).");
        }
    }
}