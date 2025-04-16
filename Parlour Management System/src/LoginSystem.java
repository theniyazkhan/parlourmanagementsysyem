package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginSystem extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox<String> roleComboBox;

    public LoginSystem() {
        setTitle("Parlor Management System - Login");
        setSize(315, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel roleLabel = new JLabel("Role:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        roleComboBox = new JComboBox<>(new String[] { "Admin", "Staff" });
        loginButton = new JButton("Login");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(roleComboBox);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                if (authenticateUser(username, password, role)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Open next window based on role
                    if (role.equals("Admin")) {
                        openAdminWindow();
                    } else {
                        openStaffWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });
    }

    private boolean authenticateUser(String username, String password, String role) {
        // Simple hardcoded authentication for demo purposes.
        // Replace this with a real database check or file storage.
        if (role.equals("Admin") && username.equals("admin") && password.equals("admin123")) {
            return true;
        } else if (role.equals("Staff") && username.equals("staff") && password.equals("staff123")) {
            return true;
        }
        return false;
    }

    private void openAdminWindow() {
        // Open admin window (or main menu)
        System.out.println("Opening Admin Window...");
        AdminWindow adminWindow = new AdminWindow();
        adminWindow.setVisible(true); // Make admin window visible
        this.dispose(); // Close the login window
    }

    private void openStaffWindow() {
        // Open staff window (or main menu)
        System.out.println("Opening Staff Window...");
        StaffWindow staffWindow = new StaffWindow();
        staffWindow.setVisible(true); // Make staff window visible
        this.dispose(); // Close the login window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginSystem().setVisible(true);
            }
        });
    }
}
