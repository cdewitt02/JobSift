import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBusinessUI extends JFrame {
    public MainBusinessUI(String name, MongoDBConnection connection) {
        setTitle("Main Business UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        //Create cards panel
        JPanel cards = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        // Create the main view panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(238, 192, 68));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());

        JLabel leftImageLabel = new JLabel(icon);
        JLabel rightImageLabel = new JLabel(icon);
        JLabel headerLabel = new JLabel("Business Center");
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

        JButton createJobButton = new JButton("Create Job");
        createJobButton.setBackground(new Color(238, 192, 68));
        createJobButton.setForeground(Color.BLACK);
        createJobButton.setBorder(buttonBorder);
        createJobButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button

        JButton viewJobsButton = new JButton("View Jobs");
        viewJobsButton.setBackground(new Color(238, 192, 68));
        viewJobsButton.setForeground(Color.BLACK);
        viewJobsButton.setBorder(buttonBorder);
        viewJobsButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button

        JButton viewBusinessButton = new JButton("View Business");
        viewBusinessButton.setBackground(new Color(238, 192, 68));
        viewBusinessButton.setForeground(Color.BLACK);
        viewBusinessButton.setBorder(buttonBorder);
        viewBusinessButton.setPreferredSize(new Dimension(150, 25)); // Set the preferred size for the button

        //Create Cards

        JPanel createCard = new JPanel();
        createCard.setLayout(new GridLayout(8, 2, 10, 10));
        createCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        createCard.setBackground(new Color(238, 192, 68));

        // GET NAME FROM SOMEWHERE ELSE?


        // Create the email label and field
        JLabel jobTitleLabel = new JLabel("Job Title:");
        JTextField jobTitleField = new JTextField();
        jobTitleLabel.setForeground(Color.BLACK);
        createCard.add(jobTitleLabel);
        createCard.add(jobTitleField);

        // Create the resume label and button
        JLabel jobDescriptionLabel = new JLabel("Job Description:");
        JTextArea jobDescriptionField = new JTextArea();
        jobDescriptionField.setLineWrap(true);
        jobDescriptionLabel.setForeground(Color.BLACK);
        createCard.add(jobDescriptionLabel);

        JScrollPane scroll = new JScrollPane (jobDescriptionField,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        createCard.add(scroll);
        // Create the skills label and field
        JLabel skillsLabel = new JLabel("Required Skills (Format: Skill1,Skill2 ... no spaces):");
        JTextField skillsField = new JTextField();
        skillsLabel.setForeground(Color.BLACK);
        createCard.add(skillsLabel);
        createCard.add(skillsField);

        // Create the locations label and field
        JLabel locationsLabel = new JLabel("Locations (Format as above):");
        JTextField locationsField = new JTextField();
        locationsLabel.setForeground(Color.BLACK);
        createCard.add(locationsLabel);
        createCard.add(locationsField);

        // Create the salary label and field
        JLabel salaryLabel = new JLabel("Preferred Salary:");
        JTextField salaryField = new JTextField();
        salaryLabel.setForeground(Color.BLACK);
        createCard.add(salaryLabel);
        createCard.add(salaryField);

        // Create the submit button
        JButton submitButton = new JButton("Post Job");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setHorizontalAlignment(JButton.CENTER);
        createCard.add(submitButton);

        // Create the back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setHorizontalAlignment(JButton.CENTER);
        createCard.add(backButton);
//        JPanel viewJobsCard = getViewJobsCard();
//        JPanel viewBusinessCard = getViewBusinessCard();

        cards.add(buttonPanel, "buttons");
        cards.add(createCard, "create");
//        cards.add(viewJobsCard, "viewJobs");
//        cards.add(viewBusinessCard, "viewBusiness");

        //Button Listeners
        createJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "create");
            }
        });
        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application

            }
        });
        viewBusinessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                cardLayout.previous(cards);
            }
        });



        mainPanel.add(headerPanel, BorderLayout.NORTH);
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0.5;
        buttonPanel.add(createJobButton, c);
        c.gridx = 1;
        c.gridwidth = 1;
        buttonPanel.add(viewJobsButton,c);
        c.gridx = 2;
        c.gridwidth = 1;
        buttonPanel.add(viewBusinessButton,c);

        mainPanel.add(cards, BorderLayout.CENTER);

        setContentPane(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the MainPersonUI class
        MongoDBConnection connection = new MongoDBConnection("mongodb://localhost:27017", "JobSiftDB");

        MainBusinessUI mainPersonUI = new MainBusinessUI("Charlie", connection);

        // Add some sample jobs



    }
}
