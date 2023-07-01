import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static com.mongodb.client.model.Filters.eq;

public class MainBusinessUI extends JFrame {
    private String email;
    private String industry = null;

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
        submitButton.setForeground(new Color(238, 192, 68));
        submitButton.setHorizontalAlignment(JButton.CENTER);
        createCard.add(submitButton);

        // Create the back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(new Color(238, 192, 68));
        backButton.setHorizontalAlignment(JButton.CENTER);
        createCard.add(backButton);
//        JPanel viewJobsCard = getViewJobsCard();
//        JPanel viewBusinessCard = getViewBusinessCard();

        //Create viewBusinessCard

        JPanel viewBusinessCard = new JPanel();
        viewBusinessCard.setLayout(new GridLayout(8, 2, 10, 10));
        viewBusinessCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        viewBusinessCard.setBackground(new Color(238, 192, 68));


        JLabel nameLabel = new JLabel("Business Name:");
        JTextField nameField = new JTextField(name);
        viewBusinessCard.add(nameLabel);
        viewBusinessCard.add(nameField);

        JLabel emailLabel = new JLabel("Contact E-mail:");
        Document doc = connection.businesses.find(new Document("name", name)).first();

        // Execute the query and get the cursor
        MongoCursor<Document> cursor = connection.businesses.find(doc).iterator();
        // Iterate over the results
        while (cursor.hasNext()) {
            Document business = cursor.next();
            // Read the desired fields from the business document
            email = business.getString("email");
            industry = business.getString("industry");
            // ... read other fields as needed
        }

        // Close the cursor

        cursor.close();
        JTextField emailField = new JTextField(email);

        JLabel industryLabel = new JLabel("Industry:");
        JTextField industryField = new JTextField(industry);

        viewBusinessCard.add(emailLabel);
        viewBusinessCard.add(emailField);
        viewBusinessCard.add(industryLabel);
        viewBusinessCard.add(industryField);

        JButton submitButton2 = new JButton("Submit Changes");
        submitButton2.setBackground(Color.BLACK);
        submitButton2.setForeground(new Color(238, 192, 68));
        submitButton2.setHorizontalAlignment(JButton.CENTER);
        viewBusinessCard.add(submitButton2);

        JButton backButton2 = new JButton("Back to Main Menu");
        backButton2.setBackground(Color.BLACK);
        backButton2.setForeground(new Color(238, 192, 68));
        backButton2.setHorizontalAlignment(JButton.CENTER);
        viewBusinessCard.add(backButton2);

        //Create viewJobs card



        cards.add(buttonPanel, "buttons");
        cards.add(createCard, "create");
//        cards.add(viewJobsCard, "viewJobs");
        cards.add(viewBusinessCard, "viewBusiness");

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
        createJobButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                createJobButton.setBackground(Color.BLACK);
                createJobButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                createJobButton.setBackground(new Color(238, 192, 68));
                createJobButton.setForeground(Color.BLACK);
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
        viewBusinessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "viewBusiness");
            }
        });
        viewBusinessButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                viewBusinessButton.setBackground(Color.BLACK);
                viewBusinessButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                viewBusinessButton.setBackground(new Color(238, 192, 68));
                viewBusinessButton.setForeground(Color.BLACK);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String company = name;
                String jobTitle = jobTitleField.getText();
                String jobDescription = jobDescriptionField.getText();
                String skills = skillsField.getText();
                String[] skillsarr = skills.split(",");
                String locations = locationsField.getText();
                String[] locationsarr = locations.split(",");
                String salary = salaryField.getText();

                connection.registerJob(company, jobTitle, jobDescription, skillsarr, locationsarr, Double.valueOf(salary));

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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                cardLayout.previous(cards);
            }
        });
        backButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                backButton.setBackground(new Color(238, 192, 68));
                backButton.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                backButton.setBackground(Color.BLACK);
                backButton.setForeground(new Color(238, 192, 68));
            }
        });
        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                cardLayout.show(cards, "buttons");
            }
        });
        backButton2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                backButton2.setBackground(new Color(238, 192, 68));
                backButton2.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {

                backButton2.setBackground(Color.BLACK);
                backButton2.setForeground(new Color(238, 192, 68));
            }
        });
        submitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                Bson filter = eq("name", name);
                Bson update = Updates.combine(
                        Updates.set("name", nameField.getText()),
                        Updates.set("email", emailField.getText()),
                        Updates.set("industry", industryField.getText())
                );
                connection.businesses.updateOne(filter, update);
            }
        });
        submitButton2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                submitButton2.setBackground(new Color(238, 192, 68));
                submitButton2.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                submitButton2.setBackground(Color.BLACK);
                submitButton2.setForeground(new Color(238, 192, 68));
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
}
