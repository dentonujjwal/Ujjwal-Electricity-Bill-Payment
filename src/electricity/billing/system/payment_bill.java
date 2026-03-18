package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class payment_bill extends JFrame implements ActionListener {
    String meter;
    String month;
    JButton upiBtn, cardBtn, netBtn, back;
    JPanel mainContent;
    CardLayout cardLayout;

    payment_bill(String meter, String month) {
        this.meter = meter;
        this.month = month;

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Start full screen
        setTitle("Bharat Electricity - Payment Portal");
        setLayout(new BorderLayout());

        // --- LEFT SIDE: Payment Methods ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 10));
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setPreferredSize(new Dimension(200, 0));

        JLabel menuLabel = new JLabel(" PAYMENT METHODS", SwingConstants.CENTER);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        leftPanel.add(menuLabel);

        upiBtn = createMenuButton("UPI / QR Code");
        cardBtn = createMenuButton("Debit / Credit Card");
        netBtn = createMenuButton("Net Banking");
        
        leftPanel.add(upiBtn);
        leftPanel.add(cardBtn);
        leftPanel.add(netBtn);

        add(leftPanel, BorderLayout.WEST);

        // --- CENTER: Content Area ---
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);

        // UPI Panel
        JPanel upiPanel = new JPanel(new BorderLayout());
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/upi_qr.png"));
            Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            JLabel qrLabel = new JLabel(new ImageIcon(i2));
            upiPanel.add(qrLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            upiPanel.add(new JLabel("QR Code Image Not Found", SwingConstants.CENTER));
        }
        JButton payUpi = new JButton("I Have Paid (UPI)");
        payUpi.addActionListener(this);
        upiPanel.add(payUpi, BorderLayout.SOUTH);
        mainContent.add(upiPanel, "UPI");

        // Card Panel
        JPanel cardInputPanel = new JPanel(new GridLayout(10, 1));
        cardInputPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        cardInputPanel.add(new JLabel("Enter Card Number:"));
        cardInputPanel.add(new JTextField());
        cardInputPanel.add(new JLabel("Expiry Date (MM/YY):"));
        cardInputPanel.add(new JTextField());
        cardInputPanel.add(new JLabel("CVV:"));
        cardInputPanel.add(new JPasswordField());
        JButton payCard = new JButton("Pay Now (Card)");
        payCard.addActionListener(this);
        cardInputPanel.add(new JLabel(""));
        cardInputPanel.add(payCard);
        mainContent.add(cardInputPanel, "CARD");

        // Netbanking Panel
        JPanel netPanel = new JPanel(new FlowLayout());
        netPanel.add(new JLabel("Select Bank:"));
        String[] banks = {"SBI", "ICICI", "HDFC", "AXIS", "PNB"};
        netPanel.add(new JComboBox<>(banks));
        JButton payNet = new JButton("Pay Now (Net Banking)");
        payNet.addActionListener(this);
        netPanel.add(payNet);
        mainContent.add(netPanel, "NET");

        add(mainContent, BorderLayout.CENTER);

        // --- RIGHT SIDE: Bharat Electricity Rules ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder(" Bharat Electricity Rules "));
        rightPanel.setPreferredSize(new Dimension(300, 0));
        rightPanel.setBackground(new Color(245, 245, 245));

        String[] rules = {
            "1. Pay before the due date to avoid late fees.",
            "2. Save electricity, save the nation.",
            "3. Online payments are 100% secure.",
            "4. Keep your transaction ID for future reference.",
            "5. Contact helpdesk at 1912 for billing issues."
        };

        for (String rule : rules) {
            JLabel rl = new JLabel("<html><body style='width: 200px; padding: 10px;'>" + rule + "</body></html>");
            rl.setFont(new Font("Serif", Font.PLAIN, 14));
            rightPanel.add(rl);
        }
        add(rightPanel, BorderLayout.EAST);

        // --- Fetch and Display Amount ---
        JLabel amountLabel = new JLabel("Loading amount...");
        amountLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        amountLabel.setForeground(new Color(0, 102, 204));
        rightPanel.add(new JLabel(" ")); // Spacer
        rightPanel.add(amountLabel);

        try {
            database c = new database();
            ResultSet rsPaid = c.statement.executeQuery("select count(*) from bill where meter_no = '" + meter + "' and status = 'Paid'");
            boolean isFirst = false;
            if (rsPaid.next() && rsPaid.getInt(1) == 0) {
                isFirst = true;
            }

            ResultSet rsVal = c.statement.executeQuery("select * from bill where meter_no = '" + meter + "' and month = '" + month + "'");
            if (rsVal.next()) {
                double total = Double.parseDouble(rsVal.getString("total_bill"));
                if (isFirst) {
                    total = total * 0.70;
                    amountLabel.setText("Total to Pay: " + String.format("%.2f", total) + " (30% OFF)");
                    amountLabel.setForeground(new Color(0, 153, 0)); // Green for discount
                } else {
                    amountLabel.setText("Total to Pay: " + String.format("%.2f", total));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(60, 60, 60));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == upiBtn) {
            cardLayout.show(mainContent, "UPI");
        } else if (e.getSource() == cardBtn) {
            cardLayout.show(mainContent, "CARD");
        } else if (e.getSource() == netBtn) {
            cardLayout.show(mainContent, "NET");
        } else if (e.getSource() == back) {
            setVisible(false);
            new pay_bill(meter);
        } else {
            // "Pay Now" or "I Have Paid" clicked
            processPayment();
        }
    }

    private void processPayment() {
        try {
            database c = new database();
            c.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '" + meter + "' and month = '" + month + "'");
            
            // 1. Core Success Message
            NotificationHandler.showSuccessDialog("Payment Successful for " + month + " bill!");
            
            // 2. Clear Notifications (No more "Notes" or "Warnings")
            String smsMsg = "Bharat Electricity: Payment Received for Meter " + meter + ".";
            NotificationHandler.sendRealSMS("8765406848", smsMsg);
            
            String emailBody = "Dear Customer, your payment for " + month + " was successful.";
            NotificationHandler.sendRealEmail("ur3707615@gmail.com", "Payment Successful", emailBody);
            
            // 3. Open Receipt
            new generate_bill(meter);

            // 4. Flight Animation logic
            startFlightAnimation();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void startFlightAnimation() {
        Timer timer = new Timer(10, new ActionListener() {
            int y = getY();
            @Override
            public void actionPerformed(ActionEvent e) {
                y -= 20; // Move up
                setLocation(getX(), y);
                if (y < -getHeight()) {
                    ((Timer)e.getSource()).stop();
                    setVisible(false);
                    // new main_class(""); // Return to main or keep exit
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new payment_bill("", "");
    }
}
