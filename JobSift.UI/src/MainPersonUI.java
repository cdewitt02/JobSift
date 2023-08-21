import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.List;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class MainPersonUI extends JFrame {
    private final String name;
    private String email;
    private File resume;
    private List<String> skills;
    private List<String> preferredLocations;
    private double salary;
    private String resumePath;
    private final MongoDBConnection connection;

    private final Set<Object> allChecked = new HashSet<>();
    private Set<Object> siftList;

    public MainPersonUI(String name, MongoDBConnection connection) {
        this.connection = connection;
        this.name = name;

        updateInfo();

        setTitle("JobSift Person");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setLocationRelativeTo(null);
        setResizable(false);

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
        ImageIcon icon = new ImageIcon("resources\\JobSift_logoSmall.png");
        setIconImage(new ImageIcon("resources\\JobSift_logo.png").getImage());

        JLabel leftImageLabel = new JLabel(icon);
        JLabel rightImageLabel = new JLabel(icon);
        JLabel headerLabel = new JLabel("JobSift");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(leftImageLabel, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(rightImageLabel, BorderLayout.EAST);

        //      Create Buttons

        Border buttonBorder = new LineBorder(Color.BLACK, 2);

        JPanel buttonPanel = new JPanel() {
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
        viewProfileButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

        JButton viewJobsButton = new JButton("View Jobs");
        viewJobsButton.setBackground(new Color(238, 192, 68));
        viewJobsButton.setForeground(Color.BLACK);
        viewJobsButton.setBorder(buttonBorder);
        viewJobsButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

        JButton siftListButton = new JButton("Sift List");
        siftListButton.setBackground(new Color(238, 192, 68));
        siftListButton.setForeground(Color.BLACK);
        siftListButton.setBorder(buttonBorder);
        siftListButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

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

        JLabel resumeLabel = new JLabel("Resume (PDF only):");
        JButton resumeButton = new JButton("Upload New Resume");
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

        JPanel viewJobsMain = new JPanel();
        viewJobsMain.setLayout(new BorderLayout());
        viewJobsMain.setBackground(new Color(238, 192, 68));

        JPanel viewJobsHeaderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        viewJobsHeaderPanel.setLayout(new GridBagLayout());
        GridBagConstraints h = new GridBagConstraints();
        h.gridy = 0;
        h.gridx = 0;
        h.insets = new Insets(5, 5, 5, 5);

        JButton backButton3 = new JButton("Back to Main Menu");
        backButton3.setBackground(Color.BLACK);
        backButton3.setForeground(new Color(238, 192, 68));

        JButton addJobsToSiftList = new JButton("Add Jobs to SiftList");
        addJobsToSiftList.setBackground(Color.BLACK);
        addJobsToSiftList.setForeground(new Color(238, 192, 68));

        viewJobsHeaderPanel.add(addJobsToSiftList, h);
        h.gridx++;
        viewJobsHeaderPanel.add(backButton3, h);

        List<Document> jobs = new ArrayList<>();

        MongoCursor<Document> cursor = connection.jobs.find().cursor();
        while (cursor.hasNext()) {
            jobs.add(cursor.next());
        }
        cursor.close();

        JPanel viewJobspanel = new JPanel(new GridBagLayout());
        viewJobspanel.setBackground(new Color(238, 192, 68));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel tutorialPanel = new JPanel(new BorderLayout());
        tutorialPanel.setBackground(new Color(238, 192, 68));
        tutorialPanel.setPreferredSize(new Dimension(450, 50));
        tutorialPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JLabel tutorialLabel1 = new JLabel("Each Panel contains info organized as JobTitle, Company, Pay. Use the checkbox to add a job to your SiftList.");

        tutorialLabel1.setForeground(Color.BLACK);
        tutorialPanel.add(tutorialLabel1, BorderLayout.NORTH);
        viewJobspanel.add(tutorialPanel, gbc);
        gbc.gridy++;

        for (Document job : jobs) {
            JPanel jobPanel = new JPanel(new BorderLayout());
            jobPanel.setBackground(new Color(238, 192, 68));
            jobPanel.setPreferredSize(new Dimension(700, 50));
            jobPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

            // Create labels for job details
            JLabel info = new JLabel(job.getString("jobTitle") + ", " + job.getString("company") + ", $" + job.get("pay") + "/hr");
            info.setForeground(Color.BLACK);
            // Create check mark button
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(new Color(238, 192, 68));

            checkBox.addActionListener(e -> {
                // Handle check mark button click event
                // Perform the necessary actions when the button is clicked
                if (checkBox.isSelected()) {
                    allChecked.add(job.get("_id"));
                } else {
                    allChecked.remove(job.get("_id"));
                }
            });

            // Add labels and check mark button to the job panel

            jobPanel.add(info, BorderLayout.CENTER);
            jobPanel.add(checkBox, BorderLayout.EAST);

            // Add job panel to the main panel
            viewJobspanel.add(jobPanel, gbc);
            gbc.gridy++;
        }

        // Create scroll pane and add the main panel to it
        JScrollPane scrollPane = new JScrollPane(viewJobspanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(238, 192, 68));

        viewJobsMain.add(viewJobsHeaderPanel, BorderLayout.NORTH);
        viewJobsMain.add(scrollPane, BorderLayout.CENTER);

        ///////////////SIFTLIST///////////////////////////
        JPanel siftMainPanel = new JPanel();
        siftMainPanel.setLayout(new BorderLayout());

        JPanel siftcards = new JPanel();
        CardLayout layout = new CardLayout();
        siftcards.setLayout(layout);

        JPanel siftbuttonPanel = new JPanel();
        siftbuttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0;
        b.gridy = 0;
        b.insets = new Insets(0, 5, 0, 5);
        b.fill = GridBagConstraints.HORIZONTAL;
        siftbuttonPanel.setBackground(new Color(238, 192, 68));
        siftbuttonPanel.setPreferredSize(new Dimension(getWidth(), 75));
        siftbuttonPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

        JButton next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(new Color(238, 192, 68));
        next.setPreferredSize(new Dimension(100, 50));

        JButton prev = new JButton("Previous");
        prev.setBackground(Color.BLACK);
        prev.setForeground(new Color(238, 192, 68));
        prev.setPreferredSize(new Dimension(100, 50));

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(new Color(238, 192, 68));
        backButton.setPreferredSize(new Dimension(150, 50));

        siftbuttonPanel.add(prev, b);
        b.gridx++;
        siftbuttonPanel.add(backButton, b);
        b.gridx++;
        siftbuttonPanel.add(next, b);

        siftMainPanel.add(siftcards, BorderLayout.CENTER);
        siftMainPanel.add(siftbuttonPanel, BorderLayout.SOUTH);

        cards.add(buttonPanel, "buttons");
        cards.add(viewJobsMain, "viewJobs");
        cards.add(viewProfileCard, "viewProfile");
        cards.add(siftMainPanel, "siftList");


        //Button Listeners
        next.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            layout.next(siftcards);
        });
        next.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                next.setBackground(new Color(238, 192, 68));
                next.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {

                next.setBackground(Color.BLACK);
                next.setForeground(new Color(238, 192, 68));
            }
        });
        prev.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            layout.previous(siftcards);
        });
        prev.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                prev.setBackground(new Color(238, 192, 68));
                prev.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                prev.setBackground(Color.BLACK);
                prev.setForeground(new Color(238, 192, 68));
            }
        });
        backButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            cardLayout.show(cards, "buttons");
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
        resumeButton.addActionListener(e -> {
            // Handle resume button click event
            // Implement the logic to upload a resume file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(MainPersonUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                resume = fileChooser.getSelectedFile();
                // Process the selected resume file
            }
        });
        viewProfileButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            updateInfo();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "viewProfile");
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
        viewJobsButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "viewJobs");
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
        siftListButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            CardLayout cl = (CardLayout) (cards.getLayout());
            for (Object job : siftList) {
                JPanel jobPanel = new JPanel();
                jobPanel.setBackground(new Color(238, 192, 68));
                jobPanel.setLayout(new GridBagLayout());
                GridBagConstraints c1 = new GridBagConstraints();
                c1.gridx = 0;
                c1.gridy = 0;
                c1.fill = GridBagConstraints.HORIZONTAL;
                Document doc = connection.jobs.find(eq("_id", job)).first();
                String skills = String.join(",", (List<String>) doc.get("requiredSkills"));
                String locationsarr = String.join(",", (List<String>) doc.get("locations"));

                JLabel jobTitleL = new JLabel("Job Title:");
                JLabel jobTitle = new JLabel(doc.getString("jobTitle"));

                JLabel companyL = new JLabel("Company:");
                JLabel company = new JLabel(doc.getString("company"));

                JLabel jobDescriptionL = new JLabel("Job Description:");
                JLabel jobDescription = new JLabel(doc.getString("jobDescription"));

                JLabel requiredSkillsL = new JLabel("Skills required:");
                JLabel requiredSkills = new JLabel(skills);

                JLabel locationsL = new JLabel("Location(s):");
                JLabel locations = new JLabel(locationsarr);

                JLabel payL = new JLabel("Pay:");
                JLabel pay = new JLabel(doc.get("pay") + " $/hr");

                JLabel emailL = new JLabel("Contact Email:");
                JLabel email = new JLabel(doc.getString("contactEmail"));

                Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                Font headerFont = new Font("Roboto", Font.BOLD, 28).deriveFont(fontAttributes);
                Font regular = new Font("Roboto", Font.PLAIN, 28);

                jobTitleL.setFont(headerFont);
                jobTitleL.setForeground(Color.BLACK);
                jobTitle.setFont(regular);
                jobTitle.setForeground(Color.BLACK);
                companyL.setFont(headerFont);
                companyL.setForeground(Color.BLACK);
                company.setFont(regular);
                company.setForeground(Color.BLACK);
                jobDescriptionL.setFont(headerFont);
                jobDescriptionL.setForeground(Color.BLACK);
                jobDescription.setFont(regular);
                jobDescription.setForeground(Color.BLACK);
                requiredSkillsL.setFont(headerFont);
                requiredSkillsL.setForeground(Color.BLACK);
                requiredSkills.setFont(regular);
                requiredSkills.setForeground(Color.BLACK);
                locationsL.setFont(headerFont);
                locationsL.setForeground(Color.BLACK);
                locations.setFont(regular);
                locations.setForeground(Color.BLACK);
                payL.setFont(headerFont);
                payL.setForeground(Color.BLACK);
                pay.setFont(regular);
                pay.setForeground(Color.BLACK);
                emailL.setFont(headerFont);
                emailL.setForeground(Color.BLACK);
                email.setFont(regular);
                email.setForeground(Color.BLACK);

                jobPanel.add(jobTitleL, c1);
                c1.gridx++;
                jobPanel.add(jobTitle, c1);
                c1.gridy++;
                c1.gridx--;
                jobPanel.add(companyL, c1);
                c1.gridx++;
                jobPanel.add(company, c1);
                c1.gridy++;
                c1.gridx--;
                jobPanel.add(jobDescriptionL, c1);
                c1.gridx++;
                jobPanel.add(jobDescription, c1);
                c1.gridy++;
                c1.gridx--;
                jobPanel.add(requiredSkillsL, c1);
                c1.gridx++;
                jobPanel.add(requiredSkills, c1);
                c1.gridy++;
                c1.gridx--;
                jobPanel.add(locationsL, c1);
                c1.gridx++;
                jobPanel.add(locations, c1);
                c1.gridy++;
                c1.gridx--;
                jobPanel.add(payL, c1);
                c1.gridx++;
                jobPanel.add(pay, c1);
                c1.gridy++;
                c1.gridx--;
                jobPanel.add(emailL, c1);
                c1.gridx++;
                jobPanel.add(email, c1);

                siftcards.add(jobPanel);
            }
            cl.show(cards, "siftList");
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

        backButton2.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            cardLayout.show(cards, "buttons");
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
        double finalSalary = salary;
        submitButton2.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            Bson filter = eq("name", name);
            String[] skillsarr = skillsField.getText().split(",");
            String[] locationsarr = locationsField.getText().split(",");

            List<String> skills_list = Arrays.asList(skillsarr);
            List<String> locations_list = Arrays.asList(locationsarr);

            Bson update;
            if (!Objects.isNull(resume)) {
                update = Updates.combine(
                        Updates.set("name", nameField.getText()),
                        Updates.set("email", emailField.getText()),
                        Updates.set("skills", skills_list),
                        Updates.set("locations", locations_list),
                        Updates.set("resume", resume.getAbsolutePath()),
                        Updates.set("salary", finalSalary)
                );
            } else {
                update = Updates.combine(
                        Updates.set("name", nameField.getText()),
                        Updates.set("email", emailField.getText()),
                        Updates.set("skills", skills_list),
                        Updates.set("locations", locations_list),
                        Updates.set("salary", finalSalary)
                );
            }
            connection.applicants.updateOne(filter, update);

            cardLayout.show(cards, "buttons");
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
        backButton3.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            cardLayout.show(cards, "buttons");
        });
        backButton3.addFocusListener(new FocusListener() {
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
        addJobsToSiftList.addActionListener(e -> {
            Bson filter = eq("name", name);
            siftList.addAll(allChecked);
            Bson update = Updates.set("siftList", siftList);
            connection.applicants.updateOne(filter, update);
        });
        addJobsToSiftList.addFocusListener(new FocusListener() {
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

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(20, 0, 40, 0);
        buttonPanel.add(viewProfileButton, c);
        c.gridy = 2;
        c.gridwidth = 1;
        buttonPanel.add(viewJobsButton, c);
        c.gridy = 4;
        c.gridwidth = 1;
        buttonPanel.add(siftListButton, c);

        mainPanel.add(cards, BorderLayout.CENTER);

        setContentPane(mainPanel);

        setVisible(true);
    }

    private void updateInfo() {
        Document doc = connection.applicants.find(new Document("name", name)).first();

        // Execute the query and get the cursor
        MongoCursor<Document> cursor = connection.applicants.find(doc).iterator();
        // Iterate over the results
        List<Object> siftListL = new ArrayList<>();
        while (cursor.hasNext()) {
            Document applicant = cursor.next();
            // Read the desired fields from the business document
            this.email = applicant.getString("email");
            this.skills = (List<String>) applicant.get("skills");
            this.preferredLocations = (List<String>) applicant.get("locations");
            this.salary = (Double) applicant.get("salary");
            this.resumePath = applicant.getString("resume");
            siftListL = (List<Object>) applicant.get("siftList");
        }

        if(Objects.isNull(siftListL)){
            this.siftList = new HashSet<>();
        }else{
            this.siftList = new HashSet<>(siftListL);
        }

        cursor.close();
    }
}
