package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class bill_analysis extends JFrame {
    String meter;
    ArrayList<String> months = new ArrayList<>();
    ArrayList<Double> amounts = new ArrayList<>();

    bill_analysis(String meter) {
        this.meter = meter;
        setSize(1000, 600);
        setLocation(200, 100);
        setTitle("Electricity Consumption Analysis / Graph");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        fetchData();

        // Custom Graph Panel
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int padding = 50;
                int labelPadding = 25;

                // Draw Axes
                g2d.drawLine(padding + labelPadding, height - padding - labelPadding, padding + labelPadding, padding);
                g2d.drawLine(padding + labelPadding, height - padding - labelPadding, width - padding, height - padding - labelPadding);

                if (amounts.isEmpty()) {
                    g2d.drawString("No billing data available for this meter.", width / 2 - 100, height / 2);
                    return;
                }

                double maxAmount = amounts.stream().max(Double::compare).orElse(1000.0);
                if (maxAmount == 0) maxAmount = 1;

                int barWidth = (width - 2 * padding - labelPadding) / Math.max(amounts.size(), 12);
                
                for (int i = 0; i < amounts.size(); i++) {
                    int x = padding + labelPadding + i * barWidth + 10;
                    int barHeight = (int) ((amounts.get(i) / maxAmount) * (height - 2 * padding - labelPadding));
                    int y = height - padding - labelPadding - barHeight;

                    // Gradient for the bar
                    GradientPaint gp = new GradientPaint(x, y, new Color(0, 102, 204), x, y + barHeight, new Color(102, 178, 255));
                    g2d.setPaint(gp);
                    g2d.fillRect(x, y, barWidth - 20, barHeight);
                    
                    // Border for the bar
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawRect(x, y, barWidth - 20, barHeight);

                    // Label the amount
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Tahoma", Font.BOLD, 10));
                    g2d.drawString(String.format("%.0f", amounts.get(i)), x, y - 5);

                    // Label the month
                    g2d.drawString(months.get(i).substring(0, 3), x, height - padding);
                }
                
                // Y-axis labels
                g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
                for (int i = 0; i < 5; i++) {
                    int y = height - padding - labelPadding - (i * (height - 2 * padding - labelPadding) / 4);
                    String label = String.format("%.0f", (maxAmount * i / 4));
                    g2d.drawString(label, padding - 20, y);
                }
            }
        };
        graphPanel.setBackground(new Color(245, 250, 255));
        add(graphPanel, BorderLayout.CENTER);

        // Sidebar for Suggestions
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        sidebar.setBackground(new Color(230, 240, 250));

        JLabel suggHeading = new JLabel("💡 Smart Suggestions");
        suggHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
        suggHeading.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(suggHeading);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] tips = {
            "• Switch to LED bulbs (saves 75% energy).",
            "• Unplug appliances when not in use.",
            "• Use natural light during the day.",
            "• Set AC to a consistent 24°C.",
            "• Clean AC filters every 2 months.",
            "• Replace old appliances with Star-rated ones.",
            "• Check for wiring leakage if bill is high."
        };

        for (String tip : tips) {
            JLabel tipLabel = new JLabel("<html><body style='width: 200px; padding-bottom: 10px;'>" + tip + "</body></html>");
            tipLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
            tipLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            sidebar.add(tipLabel);
        }

        add(sidebar, BorderLayout.EAST);

        JLabel topLabel = new JLabel("Monthly Bill Analysis for Meter: " + meter, SwingConstants.CENTER);
        topLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        topLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(topLabel, BorderLayout.NORTH);

        JButton close = new JButton("Close Analysis");
        close.setBackground(new Color(204, 0, 0));
        close.setForeground(Color.WHITE);
        close.addActionListener(e -> setVisible(false));
        add(close, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void fetchData() {
        try {
            database c = new database();
            ResultSet rs = c.statement.executeQuery("select * from bill where meter_no = '" + meter + "' order by FIELD(month, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December')");
            while (rs.next()) {
                months.add(rs.getString("month"));
                amounts.add(Double.parseDouble(rs.getString("total_bill")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new bill_analysis("");
    }
}
