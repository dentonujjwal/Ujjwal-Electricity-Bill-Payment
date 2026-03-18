package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JLabel heading ,meternumText,customerName,meterNum, address,city,state,email,phone;
    JButton next, cancel;
    TextField nameText, addressText, emailText,phoneText;
    Choice stateText, cityText;
    newCustomer(){
        super("New Customer");
        setSize(700,500);
        setLocation(400,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        customerName = new JLabel("New Customer");
        customerName.setBounds(50,80,100,20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(180,80,150,20);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,120,100,20);
        panel.add(meterNum);

        meternumText = new JLabel("");
        meternumText.setBounds(180,120,150,20);
        panel.add(meternumText);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meternumText.setText(""+ Math.abs(number) );

        address = new JLabel("Address ");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180,160,150,20);
        panel.add(addressText);

        state = new JLabel("State");
        state.setBounds(50,200,100,20);
        panel.add(state);

        stateText = new Choice();
        stateText.setBounds(180,200,150,20);
        stateText.add("Select State");
        String[] states = {
            "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", 
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", 
            "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
            "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", 
            "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", 
            "Uttar Pradesh", "Uttarakhand", "West Bengal", "Andaman and Nicobar Islands", 
            "Chandigarh", "Dadra and Nagar Haveli and Daman and Diu", "Delhi", 
            "Jammu and Kashmir", "Ladakh", "Lakshadweep", "Puducherry"
        };
        for (String s : states) {
            stateText.add(s);
        }
        panel.add(stateText);

        city = new JLabel("City");
        city.setBounds(50,240,100,20);
        panel.add(city);

        cityText = new Choice();
        cityText.setBounds(180,240,150,20);
        cityText.add("Select City");
        panel.add(cityText);

        stateText.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String selectedState = stateText.getSelectedItem();
                cityText.removeAll();
                cityText.add("Select City");
                
                if (selectedState.equals("Andhra Pradesh")) {
                    cityText.add("Visakhapatnam"); cityText.add("Vijayawada"); cityText.add("Guntur"); cityText.add("Nellore"); cityText.add("Kurnool");
                } else if (selectedState.equals("Arunachal Pradesh")) {
                    cityText.add("Itanagar"); cityText.add("Tawang"); cityText.add("Naharlagun");
                } else if (selectedState.equals("Assam")) {
                    cityText.add("Guwahati"); cityText.add("Silchar"); cityText.add("Dibrugarh"); cityText.add("Jorhat");
                } else if (selectedState.equals("Bihar")) {
                    cityText.add("Patna"); cityText.add("Gaya"); cityText.add("Bhagalpur"); cityText.add("Muzaffarpur");
                } else if (selectedState.equals("Chhattisgarh")) {
                    cityText.add("Raipur"); cityText.add("Bhilai"); cityText.add("Bilaspur"); cityText.add("Korba");
                } else if (selectedState.equals("Goa")) {
                    cityText.add("Panaji"); cityText.add("Margao"); cityText.add("Vasco da Gama");
                } else if (selectedState.equals("Gujarat")) {
                    cityText.add("Ahmedabad"); cityText.add("Surat"); cityText.add("Vadodara"); cityText.add("Rajkot"); cityText.add("Gandhinagar");
                } else if (selectedState.equals("Haryana")) {
                    cityText.add("Faridabad"); cityText.add("Gurugram"); cityText.add("Panipat"); cityText.add("Ambala");
                } else if (selectedState.equals("Himachal Pradesh")) {
                    cityText.add("Shimla"); cityText.add("Dharamshala"); cityText.add("Manali"); cityText.add("Solan");
                } else if (selectedState.equals("Jharkhand")) {
                    cityText.add("Ranchi"); cityText.add("Jamshedpur"); cityText.add("Dhanbad"); cityText.add("Bokaro");
                } else if (selectedState.equals("Karnataka")) {
                    cityText.add("Bengaluru"); cityText.add("Mysuru"); cityText.add("Hubballi"); cityText.add("Mangaluru"); cityText.add("Belagavi");
                } else if (selectedState.equals("Kerala")) {
                    cityText.add("Thiruvananthapuram"); cityText.add("Kochi"); cityText.add("Kozhikode"); cityText.add("Thrissur");
                } else if (selectedState.equals("Madhya Pradesh")) {
                    cityText.add("Indore"); cityText.add("Bhopal"); cityText.add("Gwalior"); cityText.add("Jabalpur"); cityText.add("Ujjain");
                } else if (selectedState.equals("Maharashtra")) {
                    cityText.add("Mumbai"); cityText.add("Pune"); cityText.add("Nagpur"); cityText.add("Nashik"); cityText.add("Aurangabad"); cityText.add("Thane");
                } else if (selectedState.equals("Manipur")) {
                    cityText.add("Imphal"); cityText.add("Thoubal");
                } else if (selectedState.equals("Meghalaya")) {
                    cityText.add("Shillong"); cityText.add("Tura");
                } else if (selectedState.equals("Mizoram")) {
                    cityText.add("Aizawl"); cityText.add("Lunglei");
                } else if (selectedState.equals("Nagaland")) {
                    cityText.add("Kohima"); cityText.add("Dimapur");
                } else if (selectedState.equals("Odisha")) {
                    cityText.add("Bhubaneswar"); cityText.add("Cuttack"); cityText.add("Rourkela"); cityText.add("Berhampur");
                } else if (selectedState.equals("Punjab")) {
                    cityText.add("Ludhiana"); cityText.add("Amritsar"); cityText.add("Jalandhar"); cityText.add("Patiala"); cityText.add("Bathinda");
                } else if (selectedState.equals("Rajasthan")) {
                    cityText.add("Jaipur"); cityText.add("Jodhpur"); cityText.add("Udaipur"); cityText.add("Kota"); cityText.add("Ajmer");
                } else if (selectedState.equals("Sikkim")) {
                    cityText.add("Gangtok"); cityText.add("Namchi");
                } else if (selectedState.equals("Tamil Nadu")) {
                    cityText.add("Chennai"); cityText.add("Coimbatore"); cityText.add("Madurai"); cityText.add("Tiruchirappalli"); cityText.add("Salem");
                } else if (selectedState.equals("Telangana")) {
                    cityText.add("Hyderabad"); cityText.add("Warangal"); cityText.add("Nizamabad"); cityText.add("Karimnagar");
                } else if (selectedState.equals("Tripura")) {
                    cityText.add("Agartala"); cityText.add("Udaipur");
                } else if (selectedState.equals("Uttar Pradesh")) {
                    cityText.add("Lucknow"); cityText.add("Kanpur"); cityText.add("Ghaziabad"); cityText.add("Agra"); cityText.add("Varanasi"); cityText.add("Meerut"); cityText.add("Prayagraj");
                } else if (selectedState.equals("Uttarakhand")) {
                    cityText.add("Dehradun"); cityText.add("Haridwar"); cityText.add("Roorkee"); cityText.add("Nainital");
                } else if (selectedState.equals("West Bengal")) {
                    cityText.add("Kolkata"); cityText.add("Howrah"); cityText.add("Durgapur"); cityText.add("Asansol"); cityText.add("Siliguri");
                } else if (selectedState.equals("Delhi")) {
                    cityText.add("New Delhi"); cityText.add("North Delhi"); cityText.add("South Delhi"); cityText.add("East Delhi"); cityText.add("West Delhi");
                } else if (selectedState.equals("Jammu and Kashmir")) {
                    cityText.add("Srinagar"); cityText.add("Jammu"); cityText.add("Anantnag");
                } else if (selectedState.equals("Ladakh")) {
                    cityText.add("Leh"); cityText.add("Kargil");
                } else if (selectedState.equals("Chandigarh")) {
                    cityText.add("Chandigarh");
                } else if (selectedState.equals("Puducherry")) {
                    cityText.add("Puducherry"); cityText.add("Karaikal");
                }
                cityText.add("Other");
            }
        });



        email = new JLabel("Email ");
        email.setBounds(50,280,100,20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180,280,150,20);
        panel.add(emailText);


        phone = new JLabel("Phone ");
        phone.setBounds(50,320,100,20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180,320,150,20);
        panel.add(phoneText);

        // Real-time Validation: Name (Letters only)
        nameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignore numbers/symbols
                }
            }
        });

        // Real-time Validation: Phone (Numbers only)
        phoneText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || phoneText.getText().length() >= 10) {
                    e.consume(); // Ignore letters/symbols and limit to 10
                }
            }
        });

        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230,390,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        add(imgLable,"West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==next){
            String sname = nameText.getText();
            String smeter = meternumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getSelectedItem();
            String sstate = stateText.getSelectedItem();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            // Submission Validation
            if (sname.isEmpty() || saddress.isEmpty() || scity.isEmpty() || sstate.isEmpty() || eemail.isEmpty() || sphone.isEmpty()) {
                NotificationHandler.showErrorDialog("All fields are mandatory");
                return;
            }
            if (!sname.matches("^[a-zA-Z\\s]+$")) {
                NotificationHandler.showErrorDialog("Name should only contain letters");
                return;
            }
            if (!sphone.matches("^\\d{10}$")) {
                NotificationHandler.showErrorDialog("Phone number must be exactly 10 digits");
                return;
            }
            if (!eemail.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                NotificationHandler.showErrorDialog("Please enter a valid Gmail address (e.g., user@gmail.com)");
                return;
            }


            String query_customer = "insert into new_customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
            String query_signup = "insert into Signup values('"+smeter+"','','"+sname+"','','')";

            try{
                database c = new database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                NotificationHandler.showSuccessDialog("Customer details added successfully");
                setVisible(false);
                new meterInfo(smeter);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
         new newCustomer();
    }
}
