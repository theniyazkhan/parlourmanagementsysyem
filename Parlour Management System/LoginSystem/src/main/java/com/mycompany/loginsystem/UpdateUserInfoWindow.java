
package com.mycompany.loginsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class UpdateUserInfoWindow extends JFrame {
    public UpdateUserInfoWindow(String username, JFrame parentDashboard) {
        setTitle("Update Info");
        setSize(400, 400);
        setLocationRelativeTo(parentDashboard);
        setLayout(new GridLayout(6, 2));

        // Fields to update (you can load existing info here)
        add(new JLabel("New Phone:"));
        JTextField phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("New Password:"));
        JTextField passField = new JTextField();
        add(passField);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            // Add your update logic (read users.txt, modify, rewrite)
            // Close window on success
            JOptionPane.showMessageDialog(this, "Info updated!");
            dispose();
        });

        add(updateBtn);
    }
}
