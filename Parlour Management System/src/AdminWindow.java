package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminWindow extends JFrame {
    public AdminWindow() {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton viewCustomersButton = new JButton("View Customers");
        JButton manageServicesButton = new JButton("Manage Services");

        add(viewCustomersButton);
        add(manageServicesButton);

        viewCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Viewing customer list...");
            }
        });

        manageServicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Managing services...");
            }
        });
    }
}
