package com.mycompany.loginsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegisterWindow extends JFrame {
    private JTextField nameField, dobField, phoneField, usernameField, treatmentField;
    private JPasswordField passwordField;
    private JComboBox<SkinToneOption> skinToneBox;
    private JButton registerButton, backButton;
    private JLabel suggestionLabel;

    public RegisterWindow() {
        setTitle("User Registration");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(10, 25, 49)); // Navy Blue

        JLabel logoLabel = new JLabel(new ImageIcon("logo.png"));
        JLabel nameLabel = new JLabel("Name:");
        JLabel dobLabel = new JLabel("Birth Year:");
        JLabel phoneLabel = new JLabel("Phone Number:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel skinToneLabel = new JLabel("Skin Tone:");
        JLabel treatmentLabel = new JLabel("Treatment:");

        // Set label colors
        Color labelColor = Color.WHITE;
        nameLabel.setForeground(labelColor);
        dobLabel.setForeground(labelColor);
        phoneLabel.setForeground(labelColor);
        usernameLabel.setForeground(labelColor);
        passwordLabel.setForeground(labelColor);
        skinToneLabel.setForeground(labelColor);
        treatmentLabel.setForeground(labelColor);
        
        // Input fields
        nameField = new JTextField(15);
        dobField = new JTextField(15);
        phoneField = new JTextField(15);
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        treatmentField = new JTextField(15);

        // Skin tone options with color and suggestion
        SkinToneOption[] skinTones = {
            new SkinToneOption("Fair", new Color(255, 224, 189), "Normal clean treatment"),
            new SkinToneOption("Medium", new Color(240, 200, 160), "Scrub and facial care"),
            new SkinToneOption("Olive", new Color(198, 134, 66), "Deep clean treatment"),
            new SkinToneOption("Tan", new Color(153, 101, 21), "Moisturizing facial"),
            new SkinToneOption("Brown", new Color(120, 72, 28), "Brightening facial & care"),
            new SkinToneOption("Dark", new Color(85, 52, 20), "Facial and skin care treatment")
        };

        skinToneBox = new JComboBox<>(skinTones);
        skinToneBox.setRenderer(new ListCellRenderer<SkinToneOption>() {
            private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            private final JLabel colorLabel = new JLabel();
            private final JLabel nameLabel = new JLabel();

            @Override
            public Component getListCellRendererComponent(JList<? extends SkinToneOption> list, SkinToneOption value, int index, boolean isSelected, boolean cellHasFocus) {
                colorLabel.setOpaque(true);
                colorLabel.setBackground(value.color);
                colorLabel.setPreferredSize(new Dimension(30, 20));
                nameLabel.setText(" " + value.name);

                panel.removeAll();
                panel.add(colorLabel);
                panel.add(nameLabel);

                if (isSelected) {
                    panel.setBackground(list.getSelectionBackground());
                } else {
                    panel.setBackground(list.getBackground());
                }

                return panel;
            }
        });

        // Suggestion label
        suggestionLabel = new JLabel(" ");
        suggestionLabel.setForeground(new Color(35, 203, 167));

        // Buttons
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        registerButton.setBackground(new Color(33, 150, 243));
        registerButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(33, 150, 243));
        backButton.setForeground(Color.WHITE);

        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(logoLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++; gbc.gridx = 0; add(nameLabel, gbc);
        gbc.gridx = 1; add(nameField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(dobLabel, gbc);
        gbc.gridx = 1; add(dobField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(phoneLabel, gbc);
        gbc.gridx = 1; add(phoneField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(usernameLabel, gbc);
        gbc.gridx = 1; add(usernameField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(passwordLabel, gbc);
        gbc.gridx = 1; add(passwordField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(skinToneLabel, gbc);
        gbc.gridx = 1; add(skinToneBox, gbc);

        gbc.gridy++; gbc.gridx = 1; add(suggestionLabel, gbc);
        
        gbc.gridy++; gbc.gridx = 0; add(treatmentLabel, gbc);
        gbc.gridx = 1; add(treatmentField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(backButton, gbc);
        gbc.gridx = 1; add(registerButton, gbc);

        // Listeners
        registerButton.addActionListener(e -> registerUser());
        backButton.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });

        skinToneBox.addActionListener(e -> {
            SkinToneOption selected = (SkinToneOption) skinToneBox.getSelectedItem();
            suggestionLabel.setText("Suggested: " + selected.suggestion);
        });
    }

    private void registerUser() {
        String name = nameField.getText();
        String dob = dobField.getText();
        String phone = phoneField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        SkinToneOption selected = (SkinToneOption) skinToneBox.getSelectedItem();
        String skinTone = selected.name;
        String treatment = treatmentField.getText();

        if (name.isEmpty() || dob.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(name + "," + dob + "," + phone + "," + username + "," + password + "," + skinTone + "," +treatment);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "✔️ User registered successfully!");
            new LoginWindow().setVisible(true);
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user data.");
        }
    }

    // SkinToneOption class
    static class SkinToneOption {
        String name;
        Color color;
        String suggestion;

        SkinToneOption(String name, Color color, String suggestion) {
            this.name = name;
            this.color = color;
            this.suggestion = suggestion;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
