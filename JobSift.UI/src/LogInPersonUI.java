import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LogInPersonUI extends JFrame{
    public LogInPersonUI(MongoDBConnection connection){
        // Set up the JFrame
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());
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
        JLabel titleLabel = new JLabel("JobSift User Log-In");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(leftImageLabel, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightImageLabel, BorderLayout.EAST);

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
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        // Create the password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
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
                String name = usernameField.getText();
                String password = passwordField.getText();
                String passwordenc = null;
                // Check the credentials and perform login logic
                try {
                    Document doc = connection.applicants.find(new Document("name", name)).first();

                    // Execute the query and get the cursor
                    MongoCursor<Document> cursor = connection.applicants.find(doc).iterator();
                    while (cursor.hasNext()) {
                        Document business = cursor.next();
                        passwordenc = business.getString("password");
                    }

                    cursor.close();

                    if (PasswordEncryption.checkPassword(password, passwordenc)) {
                        dispose();
                        MainPersonUI mainPersonUI = new MainPersonUI(name, connection);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password incorrect");
                    }
                }catch (Exception g){
                    JOptionPane.showMessageDialog(null, "Name not registered");
                }
            }
        });
        loginButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                loginButton.setBackground(Color.BLACK);
                loginButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                loginButton.setBackground(new Color(238, 192, 68));
                loginButton.setForeground(Color.BLACK);
            }
        });

        JButton signupButton = new JButton("Sign-Up");
        signupButton.setBackground(new Color(238, 192, 68));
        signupButton.setForeground(Color.BLACK);
        signupButton.setBorder(new LineBorder(Color.BLACK, 2));
        signupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignUpPersonUI signUpPersonUI = new SignUpPersonUI(connection);
            }
        });
        signupButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                signupButton.setBackground(Color.BLACK);
                signupButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                signupButton.setBackground(new Color(238, 192, 68));
                signupButton.setForeground(Color.BLACK);
            }
        });
        loginPanel.add(loginButton);
        loginPanel.add(signupButton);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Add the login panel to the main panel
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Add the main panel to the JFrame
        add(mainPanel);

        // Make the JFrame visible
        setVisible(true);
    }
}
