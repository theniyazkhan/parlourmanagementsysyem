package com.mycompany.loginsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class AdminWindow extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton addCustomerButton, logoutButton, viewUserButton;
    private JTextField searchField;
    private ArrayList<String[]> users;

    public AdminWindow() {
        setTitle("Admin Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Dark Blue Theme
        getContentPane().setBackground(new Color(10, 25, 49)); // Navy Blue
        UIManager.put("Label.foreground", Color.WHITE);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(10, 25, 49));

        addCustomerButton = new JButton("➕ Add Customer");
        viewUserButton = new JButton("👤 View User Info");
        logoutButton = new JButton("🔓 Logout");
        searchField = new JTextField(15);

        // Button Styles
        Color btnColor = new Color(33, 150, 243);
        Color white = Color.WHITE;
        addCustomerButton.setBackground(btnColor);
        viewUserButton.setBackground(btnColor);
        logoutButton.setBackground(btnColor);
        addCustomerButton.setForeground(white);
        viewUserButton.setForeground(white);
        logoutButton.setForeground(white);

        buttonPanel.add(addCustomerButton);
        buttonPanel.add(searchField);
        buttonPanel.add(viewUserButton);
        buttonPanel.add(logoutButton);

        // Table setup (added "Selected Services" and "Total Bill" columns)
        String[] columnNames = {
            "Name", "User Name", "Birth Year", "Contact", "Skin Tone", "Selected Services", "Total Bill"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addCustomerButton.addActionListener(e -> openRegisterWindow());
        logoutButton.addActionListener(e -> logout());
        viewUserButton.addActionListener(e -> viewUserDetails());

        loadUserData();
    }

    // Load user data from users.txt
    private void loadUserData() {
        users = new ArrayList<>();
        tableModel.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 7) {
                    users.add(data);

                    // Extract services and bill from last column
                    String[] serviceSplit = data[6].split("\\|");
                    String services = serviceSplit.length > 0 ? serviceSplit[0] : "None";
                    String bill = serviceSplit.length > 1 ? serviceSplit[1] : "0";

                    // Displayable columns (name, username, birth year, contact, skin tone, selected services, bill)
                    String[] rowData = {
                        data[0], data[3], data[1], data[2], data[5],
                        //" ", // Optional fixed default treatment
                        services,
                        bill + " BDT"
                    };

                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user data.");
        }
    }

    private void openRegisterWindow() {
        new RegisterWindow().setVisible(true);
    }

    private void logout() {
        new MainMenu().setVisible(true);
        dispose();
    }

    private void viewUserDetails() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            String[] selectedUser = users.get(selectedRow);
            String[] serviceSplit = selectedUser[6].split("\\|");
            String services = serviceSplit.length > 0 ? serviceSplit[0] : "None";
            String bill = serviceSplit.length > 1 ? serviceSplit[1] : "0";

            String userDetails = "Name: " + selectedUser[0] + "\n"
                    + "Username: " + selectedUser[3] + "\n"
                    + "Contact: " + selectedUser[2] + "\n"
                    + "Skin Tone: " + selectedUser[5] + "\n"
                    + "Services: " + services + "\n"
                    + "Total Bill: " + bill + " BDT";

            JOptionPane.showMessageDialog(this, userDetails, "User Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user from the table.");
        }
    }
}
