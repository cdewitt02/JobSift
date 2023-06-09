import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SignUpPersonUI extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JButton resumeButton;
    private JTextField skillsField;
    private JTextField locationsField;
    private JTextField salaryField;

    public SignUpPersonUI() {
        // Set up the JFrame
        setTitle("User Sign-Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
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
        JLabel titleLabel = new JLabel("JobSift User Sign-Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(leftImageLabel, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightImageLabel, BorderLayout.EAST);

        //Create Input Panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new GridLayout(8, 2, 10, 10));
        inputsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputsPanel.setBackground(new Color(238, 192, 68));

        // Create the name label and field
        JLabel nameLabel = new JLabel("Full Name:");
        nameField = new JTextField();
        nameLabel.setForeground(Color.BLACK);
        inputsPanel.add(nameLabel);
        inputsPanel.add(nameField);

        // Create the email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailLabel.setForeground(Color.BLACK);
        inputsPanel.add(emailLabel);
        inputsPanel.add(emailField);

        // Create the resume label and button
        JLabel resumeLabel = new JLabel("Resume:");
        resumeButton = new JButton("Upload Resume");
        resumeLabel.setForeground(Color.BLACK);
        resumeButton.setBackground(Color.BLACK);
        resumeButton.setForeground(Color.WHITE);
        inputsPanel.add(resumeLabel);
        inputsPanel.add(resumeButton);

        // Create the skills label and field
        JLabel skillsLabel = new JLabel("Skills:");
        skillsField = new JTextField();
        skillsLabel.setForeground(Color.BLACK);
        inputsPanel.add(skillsLabel);
        inputsPanel.add(skillsField);

        // Create the locations label and field
        JLabel locationsLabel = new JLabel("Preferred Locations:");
        locationsField = new JTextField();
        locationsLabel.setForeground(Color.BLACK);
        inputsPanel.add(locationsLabel);
        inputsPanel.add(locationsField);

        // Create the salary label and field
        JLabel salaryLabel = new JLabel("Preferred Salary:");
        salaryField = new JTextField();
        salaryLabel.setForeground(Color.BLACK);
        inputsPanel.add(salaryLabel);
        inputsPanel.add(salaryField);

        // Create the submit button
        JButton submitButton = new JButton("Register");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setHorizontalAlignment(JButton.CENTER);
        inputsPanel.add(submitButton);

        // Add action listener to the resume button
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle resume button click event
                // Implement the logic to upload a resume file
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(SignUpPersonUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Process the selected resume file
                    System.out.println("Selected Resume: " + selectedFile.getAbsolutePath());
                }
            }
        });
        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle submit button click event
                // Retrieve and process the user's sign-up data
                String name = nameField.getText();
                String email = emailField.getText();
                String skills = skillsField.getText();
                String locations = locationsField.getText();
                String salary = salaryField.getText();

                // Perform necessary actions with the sign-up data
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Skills: " + skills);
                System.out.println("Preferred Locations: " + locations);
                System.out.println("Preferred Salary: " + salary);

                // Display a confirmation message
                JOptionPane.showMessageDialog(null, "Sign up successful!");
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
