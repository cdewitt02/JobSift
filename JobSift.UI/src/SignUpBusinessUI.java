import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SignUpBusinessUI extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField passwordcField;
    private JTextField industryField;

    public SignUpBusinessUI(MongoDBConnection connection) {
        // Set up the JFrame
        setTitle("Business Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());

        // Create the main panel and layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(238, 192, 68));


        //Title
        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file

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
        JLabel titleLabel = new JLabel("JobSift Business Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(leftImageLabel, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightImageLabel, BorderLayout.EAST);

        //Create Input Panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new GridLayout(16, 1, 10, 10));
        inputsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputsPanel.setBackground(new Color(238, 192, 68));

        // Create the name label and field
        JLabel nameLabel = new JLabel("Business Name:");
        nameField = new JTextField();
        nameLabel.setForeground(Color.BLACK);
        inputsPanel.add(nameLabel);
        inputsPanel.add(nameField);

        // Create the email label and field
        JLabel emailLabel = new JLabel("Contact Email:");
        emailField = new JTextField();
        emailLabel.setForeground(Color.BLACK);
        inputsPanel.add(emailLabel);
        inputsPanel.add(emailField);

        //Create industry label and field
        JLabel industryLabel = new JLabel("Industry:");
        industryField = new JTextField();
        industryLabel.setForeground(Color.BLACK);
        inputsPanel.add(industryLabel);
        inputsPanel.add(industryField);

        //Create password labels and fields
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setForeground(Color.BLACK);
        inputsPanel.add(passwordLabel);
        inputsPanel.add(passwordField);

        //Create password labels and fields
        JLabel passwordcLabel = new JLabel("Confirm Password:");
        passwordcField = new JPasswordField();
        passwordcLabel.setForeground(Color.BLACK);
        inputsPanel.add(passwordcLabel);
        inputsPanel.add(passwordcField);

        // Create the submit button
        JButton submitButton = new JButton("Register");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setHorizontalAlignment(JButton.CENTER);
        inputsPanel.add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle submit button click event
                // Retrieve and process the user's sign-up
                String name = nameField.getText();
                String email = emailField.getText();
                String industry = industryField.getText();
                String password = passwordField.getText();
                String passwordc = passwordcField.getText();
                if(passwordc.equals(password) && !password.isEmpty()) {
                    //Call document creator
                    connection.registerBusiness(name, email, industry, password);

                    dispose();
                    MainBusinessUI mainBusinessUI = new MainBusinessUI(name, connection);
                }else{
                    JOptionPane.showMessageDialog(null, "Passwords Don't Match or is empty");
                }
            }
        });

        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputsPanel);
        // Add the main panel to the JFrame
        add(mainPanel);

        // Make the JFrame visible
        setVisible(true);
    }
}
