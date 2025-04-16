package com.mycompany.loginsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class UserDashboard extends JFrame {
    private String currentUsername;
    private JTable infoTable;
    private JLabel totalLabel;
    private JPanel servicePanel;
    private JTextField nameField, contactField, ageField, skinToneField;
    private JPasswordField passwordField;
    private String username;
    private ArrayList<String> selectedServices = new ArrayList<>();
    private int totalBill = 0;
    private JButton logoutButton, updateButton;
    
    public UserDashboard(String username) {
        this.currentUsername = username;
        setTitle("User Dashboard");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==== TOP BAR ==== 
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(20, 30, 60));
        
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(200, 50, 50));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose();  // Close current window
            new LoginWindow().setVisible(true); // Go back to login
        });
        
        // Create update button for updating user info (functionality to be implemented)
        updateButton = new JButton("Update Info");
        updateButton.setBackground(new Color(33, 150, 243));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(e -> {
            new UpdateInfoPopup(currentUsername, UserDashboard.this).setVisible(true);
        });

        
        // Panel to hold both buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(20, 30, 60));
        buttonPanel.add(updateButton);
        buttonPanel.add(logoutButton);
        
        topBar.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.WEST);
        topBar.add(buttonPanel, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // ==== MAIN CONTENT ====
        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(20, 30, 60));
        rightPanel.setBackground(new Color(20, 30, 60));

        // ==== LEFT: INFO TABLE ====
        String[] columns = {"Info", "Details"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        infoTable = new JTable(model);
        infoTable.setFont(new Font("Poppins", Font.PLAIN, 14));
        infoTable.setRowHeight(28);
        infoTable.setBackground(new Color(240, 240, 240));
        JScrollPane tableScroll = new JScrollPane(infoTable);
        leftPanel.add(tableScroll, BorderLayout.CENTER);
        loadUserInfo(model);

        // ==== RIGHT: SERVICES & CONFIRM ====
        servicePanel = new JPanel();
        servicePanel.setLayout(new BoxLayout(servicePanel, BoxLayout.Y_AXIS));
        servicePanel.setBackground(new Color(20, 30, 60));
        servicePanel.setPreferredSize(new Dimension(500, 650));

        // Adding some sample services
        addService("Facial", 2500, "facial.png");
        addService("Hairspa", 500, "hairspa.png");
        addService("Manicure", 1500, "manicure.png");
        addService("Pedicure", 1700, "pedicure.png");
        addService("Makeup", 5000, "makeup.png");

        // Total label for the total bill
        totalLabel = new JLabel(" Total: 0 BDT");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Poppins", Font.PLAIN, 18));

        // Confirm Button
        JButton confirmButton = new JButton("Confirm Services");
        confirmButton.setBackground(new Color(33, 150, 243));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> saveSelectedServices());

        // Adding service panel, total label, and confirm button to the right panel
        rightPanel.add(totalLabel, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(servicePanel), BorderLayout.CENTER);
        rightPanel.add(confirmButton, BorderLayout.SOUTH);

        // ==== FINAL LAYOUT ====
        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    // Loads the user info from the file and displays it in the table
    private void loadUserInfo(DefaultTableModel model) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6 && data[3].equals(currentUsername)) {
                    model.addRow(new String[]{"Name", data[0]});
                    model.addRow(new String[]{"Birth Year", data[1]});
                    model.addRow(new String[]{"Phone", data[2]});
                    model.addRow(new String[]{"Username", data[3]});
                    model.addRow(new String[]{"Skin Tone", data[5]});
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user info.");
        }
    }
    
    // Placeholder method for updating user info
    private void updateUserInfo() {
        // Here you would bring up a dialog or another form to let the user change their information.
        // After obtaining the new data, update the file accordingly.
        JOptionPane.showMessageDialog(this, "Update user info functionality not implemented yet.");
    }
    
    // Method to add a service card to the UI
    private void addService(String name, int price, String imagePath) {
        JPanel serviceCard = new JPanel();
        serviceCard.setBackground(new Color(40, 60, 90));
        serviceCard.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        serviceCard.setLayout(new BoxLayout(serviceCard, BoxLayout.Y_AXIS));
        serviceCard.setMaximumSize(new Dimension(200, 70));

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price: " + price + " BDT");
        priceLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        priceLabel.setForeground(Color.LIGHT_GRAY);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton selectButton = new JButton("Select");
        selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectButton.setBackground(new Color(33, 150, 243));
        selectButton.setForeground(Color.WHITE);

        selectButton.addActionListener(e -> {
            String serviceEntry = name + "(tk" + price + ")";
            if (!selectedServices.contains(serviceEntry)) {
                selectedServices.add(serviceEntry);
                totalBill += price;
                totalLabel.setText("Total: " + totalBill + " BDT");
            }
        });

        serviceCard.add(Box.createRigidArea(new Dimension(0, 5)));
         serviceCard.add(Box.createRigidArea(new Dimension(0, 5)));
        serviceCard.add(imageLabel);
        serviceCard.add(nameLabel);
        serviceCard.add(priceLabel);
        serviceCard.add(Box.createRigidArea(new Dimension(0, 5)));
        serviceCard.add(selectButton);
        serviceCard.add(Box.createRigidArea(new Dimension(0, 5)));

        servicePanel.add(serviceCard);
        servicePanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    // Save the selected services for the user to a temporary file,
    // then replace the original file with the updated one.
    private void saveSelectedServices() {
        if (selectedServices.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one service.");
            return;
        }

        File inputFile = new File("users.txt");
        File tempFile = new File("users_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 7 && data[3].equals(currentUsername)) {
                    // Append the selected services and total bill
                    String updatedLine = String.join(",", data[0], data[1], data[2], data[3], data[4], data[5])
                            + "," + String.join(";", selectedServices) + "|" + totalBill;
                    writer.write(updatedLine);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving selected services.");
            return;
        }

        // Replace the original file with the updated one
        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(this, "Services confirmed and saved!");
        } else {
            JOptionPane.showMessageDialog(this, "Error updating service information.");
        }
    }
}
