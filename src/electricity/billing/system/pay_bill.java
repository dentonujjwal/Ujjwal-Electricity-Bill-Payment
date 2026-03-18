package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {
    Choice searchmonthcho;
    String meter;
    JButton pay,back;
    JLabel cashbackLabel, totalBillText, statusText, unitText;
    boolean isFirstPayment = false;

    pay_bill(String meter){
        this.meter=meter;
        setSize(900,600);
        setLocation(300,150);
        setLayout(null);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(35,80,200,20);
        add(meterNumber);


        JLabel meterNumberText = new JLabel("");
        meterNumberText.setBounds(300,80,200,20);
        add(meterNumberText);

        JLabel name = new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);


        JLabel nameText = new JLabel("");
        nameText.setBounds(300,140,200,20);
        add(nameText);



        JLabel month = new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        searchmonthcho = new Choice();
        searchmonthcho.add("January");
        searchmonthcho.add("February");
        searchmonthcho.add("March");
        searchmonthcho.add("April");
        searchmonthcho.add("May");
        searchmonthcho.add("June");
        searchmonthcho.add("July");
        searchmonthcho.add("August");
        searchmonthcho.add("September");
        searchmonthcho.add("October");
        searchmonthcho.add("November");
        searchmonthcho.add("December");
        searchmonthcho.setBounds(300,200,150,20);
        add(searchmonthcho);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);


        unitText = new JLabel("");
        unitText.setBounds(300,260,200,20);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(35,320,200,20);
        add(totalBill);


        totalBillText = new JLabel("");
        totalBillText.setBounds(300,320,200,20);
        add(totalBillText);


        JLabel status = new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);


        statusText = new JLabel("");
        statusText.setBounds(300,380,200,20);
        statusText.setForeground(Color.RED);
        add(statusText);

        cashbackLabel = new JLabel("30% First-Time Cashback Active!");
        cashbackLabel.setBounds(35, 420, 300, 20);
        cashbackLabel.setForeground(new Color(0, 153, 0)); // Green color
        cashbackLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cashbackLabel.setVisible(false);
        add(cashbackLabel);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            while (resultSet.next()){
                meterNumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        searchmonthcho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateBillDetails();
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);


        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);

        // Initialize data for default month
        updateBillDetails();

        setVisible(true);
    }

    private void updateBillDetails() {
        database c = new database();
        try{
            // Check if there are any previous paid bills for this meter
            ResultSet rsPaid = c.statement.executeQuery("select count(*) from bill where meter_no = '"+meter+"' and status = 'Paid'");
            if (rsPaid.next() && rsPaid.getInt(1) == 0) {
                isFirstPayment = true;
                cashbackLabel.setVisible(true);
            } else {
                isFirstPayment = false;
                cashbackLabel.setVisible(false);
            }

            ResultSet resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+searchmonthcho.getSelectedItem()+"'");
            while (resultSet.next()){
                // Check if the current month is already paid
                String status = resultSet.getString("status");
                statusText.setText(status);
                
                if (status.equals("Paid")) {
                    cashbackLabel.setVisible(false); // No cashback for already paid bills
                    totalBillText.setText(resultSet.getString("total_bill"));
                } else {
                    double originalBill = Double.parseDouble(resultSet.getString("total_bill"));
                    if (isFirstPayment) {
                        double discountedBill = originalBill * 0.70;
                        totalBillText.setText(String.format("%.2f", discountedBill) + " (30% Cashback Applied)");
                    } else {
                        totalBillText.setText(resultSet.getString("total_bill"));
                    }
                }
                unitText.setText(resultSet.getString("unit"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==pay){
            setVisible(false);
            new payment_bill(meter, searchmonthcho.getSelectedItem());
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
