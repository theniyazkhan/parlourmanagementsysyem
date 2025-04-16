package com.mycompany.loginsystem;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UpdateInfoPopup extends JFrame {
    private JTextField nameField, ageField, phoneField, skinToneField;
    private JPasswordField passwordField;
    private String currentUsername;
    private JFrame parent;

    public UpdateInfoPopup(String username, JFrame parentFrame) {
        this.currentUsername = username;
        this.parent = parentFrame;

        setTitle("Update Your Information");
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        Font font = new Font("Poppins", Font.PLAIN, 14);
        Color bg = new Color(30, 40, 70);
        getContentPane().setBackground(bg);

        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Birth Year:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel skinToneLabel = new JLabel("Skin Tone:");
        JLabel passLabel = new JLabel("Password:");

        nameLabel.setForeground(Color.WHITE);
        ageLabel.setForeground(Color.WHITE);
        phoneLabel.setForeground(Color.WHITE);
        skinToneLabel.setForeground(Color.WHITE);
        passLabel.setForeground(Color.WHITE);

        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();
        skinToneField = new JTextField();
        passwordField = new JPasswordField();

        JButton saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(33, 150, 243));
        saveButton.setForeground(Color.WHITE);

        saveButton.addActionListener(e -> saveUpdatedInfo());

        add(nameLabel); add(nameField);
        add(ageLabel); add(ageField);
        add(phoneLabel); add(phoneField);
        add(skinToneLabel); add(skinToneField);
        add(passLabel); add(passwordField);
        add(new JLabel("")); add(saveButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadCurrentData();
    }

    private void loadCurrentData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6 && data[3].equals(currentUsername)) {
                    nameField.setText(data[0]);
                    ageField.setText(data[1]);
                    phoneField.setText(data[2]);
                    skinToneField.setText(data[5]);
                    passwordField.setText(data[4]);
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user data.");
        }
    }

    private void saveUpdatedInfo() {
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String phone = phoneField.getText().trim();
        String skinTone = skinToneField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || skinTone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        File inputFile = new File("users.txt");
        File tempFile = new File("users_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[3].equals(currentUsername)) {
                    String newLine = name + "," + age + "," + phone + "," + currentUsername + "," + password + "," + skinTone;
                    if (data.length >= 7) {
                        newLine += "," + data[6]; // keep treatments
                    }
                    writer.write(newLine);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating user data.");
            return;
        }

        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(this, "Information updated successfully!");
            this.dispose();
            parent.dispose(); // Close dashboard and reopen to refresh
            new UserDashboard(currentUsername).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Error finalizing update.");
        }
    }
}
