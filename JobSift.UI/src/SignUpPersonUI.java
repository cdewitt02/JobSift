import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;


public class SignUpPersonUI extends JFrame {
    private JTextField nameField;
    private JTextField passwordField;
    private JTextField emailField;
    private JButton resumeButton;
    private JTextField skillsField;
    private JTextField locationsField;
    private JTextField salaryField;
    private JTextField jobSeekField;
    private String resume;

    public SignUpPersonUI(MongoDBConnection connection) {
        // Set up the JFrame
        setTitle("User Sign-Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
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
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(leftImageLabel, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightImageLabel, BorderLayout.EAST);

        //Create Input Panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new GridLayout(16, 2, 10, 10));
        inputsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputsPanel.setBackground(new Color(238, 192, 68));

        // Create the name label and field
        JLabel nameLabel = new JLabel("Full Name:");
        nameField = new JTextField();
        nameLabel.setForeground(Color.BLACK);
        inputsPanel.add(nameLabel);
        inputsPanel.add(nameField);

        // Create the name label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setForeground(Color.BLACK);
        inputsPanel.add(passwordLabel);
        inputsPanel.add(passwordField);

        // Create the name label and field
        JLabel passwordcLabel = new JLabel("Confirm Password:");
        JTextField passwordcField = new JPasswordField();
        passwordcLabel.setForeground(Color.BLACK);
        inputsPanel.add(passwordcLabel);
        inputsPanel.add(passwordcField);


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
        resumeButton.setForeground(new Color(238, 192, 68));
        inputsPanel.add(resumeLabel);
        inputsPanel.add(resumeButton);

        // Create the skills label and field
        JLabel skillsLabel = new JLabel("Skills (Format: Skill1,Skill2 ... no spaces):");
        skillsField = new JTextField();
        skillsLabel.setForeground(Color.BLACK);
        inputsPanel.add(skillsLabel);
        inputsPanel.add(skillsField);

        // Create the locations label and field
        JLabel locationsLabel = new JLabel("Preferred Locations (Format as above):");
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
        submitButton.setForeground(new Color(238, 192, 68));
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
                    byte[] fileContent = readFileAsBytes(selectedFile.getAbsolutePath());
                    // Encode the file content to Base64
                    String encodedFileContent = Base64.getEncoder().encodeToString(fileContent);
                    resume = encodedFileContent;
                }
            }
        });
        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Handle submit button click event
                String name = nameField.getText();
                String password = passwordField.getText();
                String passwordc = passwordcField.getText();
                String email = emailField.getText();
                String skills = skillsField.getText();
                String[] skillsarr = skills.split(",");
                String locations = locationsField.getText();
                String[] locationsarr = locations.split(",");
                String salary = salaryField.getText();

                if(passwordc.equals(password) && !password.isEmpty()) {
                    //Call document creator
                    connection.registerApplicant(name, password, email, skillsarr, locationsarr, Double.valueOf(salary), resume);

                    // Display a confirmation message
                    JOptionPane.showMessageDialog(null, "Sign up successful!");

                    dispose();
                    MainPersonUI mainPersonUI = new MainPersonUI(name, connection);
                }else{
                    JOptionPane.showMessageDialog(null, "Passwords don't match or are empty");
                }
            }
        });
        submitButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                submitButton.setBackground(new Color(238, 192, 68));
                submitButton.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                submitButton.setBackground(Color.BLACK);
                submitButton.setForeground(new Color(238, 192, 68));
            }
        });

        resumeButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                resumeButton.setBackground(new Color(238, 192, 68));
                resumeButton.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                resumeButton.setBackground(Color.BLACK);
                resumeButton.setForeground(new Color(238, 192, 68));
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
    // Helper method to read a file's content into a byte array
    private static byte[] readFileAsBytes(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileContent = new byte[(int) file.length()];
            fileInputStream.read(fileContent);
            fileInputStream.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}