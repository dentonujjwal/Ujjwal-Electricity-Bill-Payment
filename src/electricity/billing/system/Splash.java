package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    private Thread t;
 
    Splash() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setUndecorated(true);
        setLayout(null);

        // Get Screen Dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        try {
            // 1. Background Image - Using the same path as the original code
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/splash/splash.png"));
            Image i2 = i1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(i2));
            imageLabel.setBounds(0, 0, width, height);
            add(imageLabel);

            // 2. Welcome Text (Centered)
            JLabel welcomeText = new JLabel("Welcome to Electricity Services", SwingConstants.CENTER);
            welcomeText.setFont(new Font("Tahoma", Font.BOLD, 75));
            welcomeText.setForeground(Color.WHITE);
            welcomeText.setBounds(0, (height / 2) - 50, width, 100);
            
            // Text Shadow for depth
            JLabel shadow = new JLabel("Welcome to Electricity Services", SwingConstants.CENTER);
            shadow.setFont(new Font("Tahoma", Font.BOLD, 75));
            shadow.setForeground(new Color(0, 0, 0, 180));
            shadow.setBounds(5, (height / 2) - 45, width, 100);
            
            imageLabel.add(welcomeText);
            imageLabel.add(shadow);

            // 4. Modern Progress Bar
            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setBounds((width / 2) - 250, (height / 2) + 120, 500, 6);
            progressBar.setBackground(new Color(60, 60, 60));
            progressBar.setForeground(new Color(0, 255, 127)); // Professional green
            progressBar.setBorderPainted(false);
            imageLabel.add(progressBar);

        } catch (Exception e) {
            e.printStackTrace();
            getContentPane().setBackground(new Color(25, 25, 25));
            JLabel fallbackText = new JLabel("Welcome to Electricity Services", SwingConstants.CENTER);
            fallbackText.setFont(new Font("Tahoma", Font.BOLD, 60));
            fallbackText.setForeground(Color.WHITE);
            fallbackText.setBounds(0, height / 2 - 50, width, 100);
            add(fallbackText);
        }

        setVisible(true);

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4500); // 4.5 seconds for a proper feel
            setVisible(false);
            dispose();
            new Login(); // Opens Login screen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
