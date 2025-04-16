package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StaffWindow extends JFrame {
    private JButton addCustomerButton;
    private JButton viewCustomerButton;
    private JTextArea customerListTextArea; // To display the list of customers

    // Simulating a list of customers for now (in-memory)
    private DefaultListModel<String> customerList;

    public StaffWindow() {
        setTitle("Staff Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        customerList = new DefaultListModel<>(); // This will hold the customer data
        customerListTextArea = new JTextArea(10, 40);
        customerListTextArea.setEditable(false);

        addCustomerButton = new JButton("Add Customer");
        viewCustomerButton = new JButton("View Customer");

        add(addCustomerButton);
        add(viewCustomerButton);
        add(new JScrollPane(customerListTextArea)); // To make the text area scrollable

        // Event listener for adding a customer
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        viewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCustomers();
            }
        });
    }

    private void addCustomer() {
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(3);
        JTextField contactField = new JTextField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);

        int option = JOptionPane.showConfirmDialog(this, panel, "Enter Customer Details", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String age = ageField.getText();
            String contact = contactField.getText();

            String customerInfo = "Name: " + name + ", Age: " + age + ", Contact: " + contact;
            customerList.addElement(customerInfo);

            JOptionPane.showMessageDialog(this, "Customer Added Successfully!");
        }
    }

    private void viewCustomers() {
        customerListTextArea.setText("");

        for (int i = 0; i < customerList.size(); i++) {
            customerListTextArea.append(customerList.get(i) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StaffWindow().setVisible(true);
            }
        });
    }
}
