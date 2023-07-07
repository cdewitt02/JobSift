import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MainPersonUI extends JFrame {
    private DefaultListModel<String> jobListModel;
    private JList<String> jobList;

    public MainPersonUI(String name, MongoDBConnection connection) {
        setTitle("Main Person UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        //Create cards panel
        JPanel cards = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(238, 192, 68));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));


        // Create the label for the header
        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());

        JLabel leftImageLabel = new JLabel(icon);
        JLabel rightImageLabel = new JLabel(icon);
        JLabel headerLabel = new JLabel("JobSift");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(leftImageLabel, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(rightImageLabel, BorderLayout.EAST);

        //      Create Buttons

        Border buttonBorder = new LineBorder(Color.BLACK, 2);

        JPanel buttonPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBackground(new Color(238, 192, 68));
        viewProfileButton.setForeground(Color.BLACK);
        viewProfileButton.setBorder(buttonBorder);
        viewProfileButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button

        JButton viewJobsButton = new JButton("View Jobs");
        viewJobsButton.setBackground(new Color(238, 192, 68));
        viewJobsButton.setForeground(Color.BLACK);
        viewJobsButton.setBorder(buttonBorder);
        viewJobsButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button

        JButton siftListButton = new JButton("Sift List");
        siftListButton.setBackground(new Color(238, 192, 68));
        siftListButton.setForeground(Color.BLACK);
        siftListButton.setBorder(buttonBorder);
        siftListButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button


        cards.add(buttonPanel, "buttons");
//        cards.add(createCard, "create");
////        cards.add(viewJobsCard, "viewJobs");
//        cards.add(viewBusinessCard, "viewBusiness");

        //Button Listeners
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "create");
            }
        });
        viewProfileButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                viewProfileButton.setBackground(Color.BLACK);
                viewProfileButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                viewProfileButton.setBackground(new Color(238, 192, 68));
                viewProfileButton.setForeground(Color.BLACK);
            }
        });
        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "viewJobs");
            }
        });
        viewJobsButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                viewJobsButton.setBackground(Color.BLACK);
                viewJobsButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                viewJobsButton.setBackground(new Color(238, 192, 68));
                viewJobsButton.setForeground(Color.BLACK);
            }
        });
        siftListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "viewBusiness");
            }
        });
        siftListButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                siftListButton.setBackground(Color.BLACK);
                siftListButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                siftListButton.setBackground(new Color(238, 192, 68));
                siftListButton.setForeground(Color.BLACK);
            }
        });
//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String company = name;
//                String jobTitle = jobTitleField.getText();
//                String jobDescription = jobDescriptionField.getText();
//                String skills = skillsField.getText();
//                String[] skillsarr = skills.split(",");
//                String locations = locationsField.getText();
//                String[] locationsarr = locations.split(",");
//                String salary = salaryField.getText();
//
//                connection.registerJob(company, jobTitle, jobDescription, skillsarr, locationsarr, Double.valueOf(salary));
//
//            }
//        });
//        submitButton.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                submitButton.setBackground(new Color(238, 192, 68));
//                submitButton.setForeground(Color.BLACK);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                submitButton.setBackground(Color.BLACK);
//                submitButton.setForeground(new Color(238, 192, 68));
//            }
//        });
//
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Handle job seeker button click event
//                // Perform the necessary actions when the user indicates they are seeking a job
//                // For example, navigate to the job seeker section of your application
//                cardLayout.previous(cards);
//            }
//        });
//        backButton.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                backButton.setBackground(new Color(238, 192, 68));
//                backButton.setForeground(Color.BLACK);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                backButton.setBackground(Color.BLACK);
//                backButton.setForeground(new Color(238, 192, 68));
//            }
//        });
//        backButton2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Handle job seeker button click event
//                // Perform the necessary actions when the user indicates they are seeking a job
//                // For example, navigate to the job seeker section of your application
//                cardLayout.show(cards, "buttons");
//            }
//        });
//        backButton2.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                backButton2.setBackground(new Color(238, 192, 68));
//                backButton2.setForeground(Color.BLACK);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//
//                backButton2.setBackground(Color.BLACK);
//                backButton2.setForeground(new Color(238, 192, 68));
//            }
//        });
//        submitButton2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Handle job seeker button click event
//                // Perform the necessary actions when the user indicates they are seeking a job
//                // For example, navigate to the job seeker section of your application
//                Bson filter = eq("name", name);
//                Bson update = Updates.combine(
//                        Updates.set("name", nameField.getText()),
//                        Updates.set("email", emailField.getText()),
//                        Updates.set("industry", industryField.getText())
//                );
//                connection.businesses.updateOne(filter, update);
//            }
//        });
//        submitButton2.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                submitButton2.setBackground(new Color(238, 192, 68));
//                submitButton2.setForeground(Color.BLACK);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                submitButton2.setBackground(Color.BLACK);
//                submitButton2.setForeground(new Color(238, 192, 68));
//            }
//        });

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(0, 0, 20, 0);
        buttonPanel.add(viewProfileButton, c);
        c.gridy = 2;
        c.gridwidth = 1;
        buttonPanel.add(viewJobsButton,c);
        c.gridy = 4;
        c.gridwidth = 1;
        buttonPanel.add(siftListButton,c);

        mainPanel.add(cards, BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the MainPersonUI class
        MongoDBConnection connection = new MongoDBConnection("mongodb://localhost:27017", "JobSiftDB");
        MainPersonUI mainPersonUI = new MainPersonUI("Charlie", connection);
    }

}

