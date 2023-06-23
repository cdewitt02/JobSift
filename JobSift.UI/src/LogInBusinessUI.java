import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInBusinessUI extends JFrame{
    public LogInBusinessUI(MongoDBConnection connection){
        // Set up the JFrame
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());


        // Create the main panel and layout
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file

        //Title
        JPanel titlePanel = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        JLabel leftImageLabel = new JLabel(icon);
        JLabel rightImageLabel = new JLabel(icon);
        JLabel titleLabel = new JLabel("JobSift Business Log-In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(leftImageLabel, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightImageLabel, BorderLayout.EAST);

        // Create the login panel
        JPanel loginPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the username label and field
        JLabel usernameLabel = new JLabel("Name of Business:");
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        // Create the password label and field
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JPasswordField();
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        // Create the login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(238, 192, 68));
        loginButton.setForeground(Color.BLACK);
        loginButton.setBorder(new LineBorder(Color.BLACK, 2));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the necessary actions for login
                String username = usernameField.getText();
                String password = passwordField.getText();
                // Check the credentials and perform login logic
                // For example, validate username and password against a database

                // Display a message based on the login result
                if (username.equals("admin") && String.valueOf(password).equals("password")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }

                // Clear the fields after login attempt
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        JButton signupButton = new JButton("Register Business");
        signupButton.setBackground(new Color(238, 192, 68));
        signupButton.setForeground(Color.BLACK);
        signupButton.setBorder(new LineBorder(Color.BLACK, 2));
        signupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignUpBusinessUI signUpBusinessUI = new SignUpBusinessUI(connection);
            }
        });
        loginPanel.add(loginButton);
        loginPanel.add(signupButton);


        // Add the login panel to the main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Add the main panel to the JFrame
        add(mainPanel);

        // Make the JFrame visible
        setVisible(true);
    }
}
