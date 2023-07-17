import javax.print.Doc;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class WelcomePageUI extends JFrame {
    private JButton jobSeekerButton;
    private JButton businessButton;

    public WelcomePageUI(MongoDBConnection connection) {
        // Set up the JFrame
        setTitle("Welcome to Job Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());

        // Create the main panel and layout
        JPanel mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file

        // Create the welcome label
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
        JLabel titleLabel = new JLabel("Welcome to Job Portal!");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase the font size to 24
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(leftImageLabel, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightImageLabel, BorderLayout.EAST);


        // Create the question label
        JLabel questionLabel = new JLabel("Are you seeking a job or looking to fill a role in your business?");
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase the font size to 24
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the buttons panel
        JPanel buttonPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setLayout(new FlowLayout());
        Border buttonBorder = new LineBorder(Color.BLACK, 2);


        // Create the job seeker button
        jobSeekerButton = new JButton("I'm seeking a job");
        jobSeekerButton.setBackground(new Color(238, 192, 68));
        jobSeekerButton.setForeground(Color.BLACK);
        jobSeekerButton.setBorder(buttonBorder);
        jobSeekerButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button


        jobSeekerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                dispose();
                LogInPersonUI logInPersonUI = new LogInPersonUI(connection);
            }
        });
        jobSeekerButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jobSeekerButton.setBackground(Color.BLACK);
                jobSeekerButton.setForeground(new Color(238, 192, 68));
                jobSeekerButton.setBorder(buttonBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jobSeekerButton.setBackground(new Color(238, 192, 68));
                jobSeekerButton.setForeground(Color.BLACK);
                jobSeekerButton.setBorder(buttonBorder);
            }
        });

        // Create the business button
        businessButton = new JButton("I'm looking to fill a role");
        businessButton.setBackground(new Color(238, 192, 68));
        businessButton.setForeground(Color.BLACK);
        businessButton.setBorder(buttonBorder);
        businessButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button


        businessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle business button click event
                // Perform the necessary actions when the user indicates they are looking to fill a role
                // For example, navigate to the business section of your application
                dispose();
                LogInBusinessUI logInBusinessUI = new LogInBusinessUI(connection);
            }
        });
        businessButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                businessButton.setBackground(Color.BLACK);
                businessButton.setForeground(new Color(238, 192, 68));
                businessButton.setBorder(buttonBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                businessButton.setBackground(new Color(238, 192, 68));
                businessButton.setForeground(Color.BLACK);
                businessButton.setBorder(buttonBorder);
            }
        });

        // Add components to the main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(questionLabel, BorderLayout.CENTER);
        buttonPanel.add(jobSeekerButton);
        buttonPanel.add(businessButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the JFrame
        add(mainPanel);

        // Make the JFrame visible
        setVisible(true);
    }

    public static void main (String[] args){
        MongoDBConnection connection = new MongoDBConnection("mongodb://localhost:27017", "JobSiftDB");
        WelcomePageUI welcomePageUI = new WelcomePageUI(connection);


    }
}
