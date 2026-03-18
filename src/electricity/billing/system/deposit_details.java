package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho,searchMonthCho;
    JTable table;
    JButton search,print,close;
    JLabel nameLabel; // To show selected meter's name
    deposit_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.add("All Meters"); // Added 'All' option
        searchMeterCho.setBounds(180,20,150,20);
        add(searchMeterCho);

        try{
            database c= new database();
            ResultSet resultSet = c.statement.executeQuery("select distinct meter_no from bill");
            while (resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Customer Name:");
        labelName.setBounds(20, 45, 120, 20);
        add(labelName);

        nameLabel = new JLabel("");
        nameLabel.setBounds(150, 45, 150, 20);
        nameLabel.setForeground(Color.BLUE);
        add(nameLabel);

        searchMeterCho.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                updateSelectedName();
                performSearch();
            }
        });
        
        // Initialize name for first item
        updateSelectedName();

        JLabel searchMonth = new JLabel("Search By Month");
        searchMonth.setBounds(400,20,100,20);
        add(searchMonth);

        searchMonthCho = new Choice();
        searchMonthCho.add("All Months"); // Added 'All' option
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(520,20,150,20);
        add(searchMonthCho);

        searchMonthCho.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                performSearch();
            }
        });



        table = new JTable();
        try{
            database c =new database();
            ResultSet resultSet = c.statement.executeQuery("select b.meter_no, c.name, b.month, b.unit, b.total_bill, b.status from bill b join new_customer c on b.meter_no = c.meter_no");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    private void updateSelectedName() {
        String selectedMeter = searchMeterCho.getSelectedItem();
        if (selectedMeter.equals("All Meters")) {
            nameLabel.setText("N/A");
            return;
        }
        try {
            database c = new database();
            ResultSet rs = c.statement.executeQuery("select name from new_customer where meter_no = '" + selectedMeter + "'");
            if (rs.next()) {
                nameLabel.setText(rs.getString("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void performSearch() {
        String meter = searchMeterCho.getSelectedItem();
        String month = searchMonthCho.getSelectedItem();
        
        StringBuilder query = new StringBuilder("select b.meter_no, c.name, b.month, b.unit, b.total_bill, b.status from bill b join new_customer c on b.meter_no = c.meter_no");
        
        boolean hasMeter = !meter.equals("All Meters");
        boolean hasMonth = !month.equals("All Months");

        if (hasMeter || hasMonth) {
            query.append(" where ");
            if (hasMeter) {
                query.append("b.meter_no = '").append(meter).append("'");
            }
            if (hasMonth) {
                if (hasMeter) query.append(" and ");
                query.append("b.month = '").append(month).append("'");
            }
        }
        
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery(query.toString());
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            performSearch();
        } else if (e.getSource()==print)
        {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }

        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit_details();
    }
}
