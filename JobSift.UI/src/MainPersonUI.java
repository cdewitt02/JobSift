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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class MainPersonUI extends JFrame {
    private String name;
    private String email;
    private File resume;
    private List<String> skills;
    private List<String> preferredLocations;
    private double salary;
    private String resumePath;
    private MongoDBConnection connection;

    public MainPersonUI(String name, MongoDBConnection connection) {
        this.connection = connection;
        this.name = name;
        updateInfo();

        setTitle("Main Person UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
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
        
        //Create viewProfileCard

        JPanel viewProfileCard = new JPanel();
        viewProfileCard.setLayout(new GridLayout(9, 2, 10, 10));
        viewProfileCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        viewProfileCard.setBackground(new Color(238, 192, 68));

        JLabel nameLabel = new JLabel("Business Name:");
        JTextField nameField = new JTextField(name);
        nameLabel.setForeground(Color.BLACK);

        JLabel emailLabel = new JLabel("Contact E-mail:");
        JTextField emailField = new JTextField(email);
        emailLabel.setForeground(Color.BLACK);

        String skillsstr = String.join(",", skills);
        JLabel skillsLabel = new JLabel("Skills:");
        skillsLabel.setForeground(Color.BLACK);
        JTextField skillsField = new JTextField(skillsstr);

        String locationsstr = String.join(",", preferredLocations);
        JLabel locationsLabel = new JLabel("Preferred Locations:");
        locationsLabel.setForeground(Color.BLACK);
        JTextField locationsField = new JTextField(locationsstr);

        String salarystr = String.valueOf(salary);
        JLabel salaryLabel = new JLabel("Preferred pay:");
        salaryLabel.setForeground(Color.BLACK);
        JTextField salaryField = new JTextField(salarystr);

        JButton resumeButton = new JButton();
        JLabel resumeLabel = new JLabel("Resume:");
        JLabel currResumeLabel = new JLabel("Current Resume:");
        JLabel currresume = new JLabel(resumePath);
        currresume.setForeground(Color.BLACK);
        currResumeLabel.setForeground(Color.BLACK);
        resumeButton = new JButton("Upload New Resume");
        resumeLabel.setForeground(Color.BLACK);
        resumeButton.setBackground(Color.BLACK);
        resumeButton.setForeground(new Color(238, 192, 68));

        viewProfileCard.add(nameLabel);
        viewProfileCard.add(nameField);
        viewProfileCard.add(emailLabel);
        viewProfileCard.add(emailField);
        viewProfileCard.add(skillsLabel);
        viewProfileCard.add(skillsField);
        viewProfileCard.add(locationsLabel);
        viewProfileCard.add(locationsField);
        viewProfileCard.add(salaryLabel);
        viewProfileCard.add(salaryField);
        viewProfileCard.add(resumeLabel);
        viewProfileCard.add(resumeButton);
        viewProfileCard.add(currResumeLabel);
        viewProfileCard.add(currresume);

        JButton submitButton2 = new JButton("Submit Changes");
        submitButton2.setBackground(Color.BLACK);
        submitButton2.setForeground(new Color(238, 192, 68));
        submitButton2.setHorizontalAlignment(JButton.CENTER);
        viewProfileCard.add(submitButton2);

        JButton backButton2 = new JButton("Back to Main Menu");
        backButton2.setBackground(Color.BLACK);
        backButton2.setForeground(new Color(238, 192, 68));
        backButton2.setHorizontalAlignment(JButton.CENTER);
        viewProfileCard.add(backButton2);

        //viewJobsCard
        List<Document> jobs = new ArrayList<>();

        MongoCursor<Document> cursor = connection.jobs.find().cursor();
        while (cursor.hasNext()){
            jobs.add(cursor.next());
        }
        cursor.close();


        JPanel viewJobsCard = new JPanel(new GridLayout(jobs.size(), 1));
        viewJobsCard.setBackground(new Color(238, 192, 68)); // Set background color

        for (Document job : jobs) {
            JPanel jobPanel = new JPanel(new BorderLayout());
            jobPanel.setBackground(Color.WHITE); // Set job entry background color

            // Create labels for job details
            JLabel jobTitle = new JLabel("Job Title: " + job.getString("jobTitle"));
            JLabel companyLabel = new JLabel("Company: " + job.getString("company"));
            JLabel payLabel = new JLabel("Pay: " + job.get("pay"));

            // Create check mark button
            JCheckBox checkBox = new JCheckBox();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle check mark button click event
                    // Perform the necessary actions when the button is clicked

                }
            });

            // Add labels and check mark button to the job panel
            jobPanel.add(jobTitle, BorderLayout.NORTH);
            jobPanel.add(companyLabel, BorderLayout.CENTER);
            jobPanel.add(payLabel, BorderLayout.SOUTH);
            jobPanel.add(checkBox, BorderLayout.EAST);

            // Add job panel to the main panel
            viewJobsCard.add(jobPanel);
        }

        // Create scroll pane and add the main panel to it
        JScrollPane scrollPane = new JScrollPane(viewJobsCard);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        cards.add(buttonPanel, "buttons");
//        cards.add(createCard, "create");
        cards.add(scrollPane, "viewJobs");
        cards.add(viewProfileCard, "viewProfile");

        //Button Listeners
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle resume button click event
                // Implement the logic to upload a resume file
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(MainPersonUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    resume = selectedFile;
                    // Process the selected resume file
                    System.out.println("Selected Resume: " + selectedFile.getAbsolutePath());
                }
            }
        });
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                updateInfo();
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "viewProfile");
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
        Double finalSalary = salary;
        submitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle job seeker button click event
                // Perform the necessary actions when the user indicates they are seeking a job
                // For example, navigate to the job seeker section of your application
                Bson filter = eq("name", name);
                String[] skillsarr = skillsField.getText().split(",");
                String[] locationsarr = locationsField.getText().split(",");

                List<String> skills_list = Arrays.asList(skillsarr);
                List<String> locations_list = Arrays.asList(locationsarr);

                Bson update;
                if(!Objects.isNull(resume)){
                    update = Updates.combine(
                            Updates.set("name", nameField.getText()),
                            Updates.set("email", emailField.getText()),
                            Updates.set("skills", skills_list),
                            Updates.set("locations", locations_list),
                            Updates.set("resume", resume.getAbsolutePath()),
                            Updates.set("salary", Double.valueOf(finalSalary))
                    );
                }else{
                    update = Updates.combine(
                            Updates.set("name", nameField.getText()),
                            Updates.set("email", emailField.getText()),
                            Updates.set("skills", skills_list),
                            Updates.set("locations", locations_list),
                            Updates.set("salary", Double.valueOf(finalSalary))
                    );
                }
                connection.applicants.updateOne(filter, update);

                dispose();
                MainPersonUI mainPersonUI = new MainPersonUI(name, connection);
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

    private void updateInfo(){
        Document doc = connection.applicants.find(new Document("name", name)).first();

        // Execute the query and get the cursor
        MongoCursor<Document> cursor = connection.applicants.find(doc).iterator();
        // Iterate over the results

        while (cursor.hasNext()) {
            Document applicant = cursor.next();
            // Read the desired fields from the business document
            this.email = applicant.getString("email");
            this.skills = (List<String>)applicant.get("skills");
            this.preferredLocations = (List<String>)applicant.get("locations");
            this.salary = (Double)applicant.get("salary");
            this.resumePath = applicant.getString("resume");
        }
        cursor.close();
    }

    public static void main(String[] args) {
        // Create an instance of the MainPersonUI class
        MongoDBConnection connection = new MongoDBConnection("mongodb://localhost:27017", "JobSiftDB");
        MainPersonUI mainPersonUI = new MainPersonUI("123", connection);
    }

}

