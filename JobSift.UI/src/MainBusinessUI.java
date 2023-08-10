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
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class MainBusinessUI extends JFrame {
    private String email;
    private List<Object> allChecked = new ArrayList<>();
    private Set<Object> siftList = new HashSet<>();

    public MainBusinessUI(String name, MongoDBConnection connection) {
        setTitle("Main Business UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setLocationRelativeTo(null);
        setResizable(false);

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

        //label for header
        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());

        JLabel leftImageLabel = new JLabel(icon);
        JLabel rightImageLabel = new JLabel(icon);
        JLabel headerLabel = new JLabel("Business Center");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLACK);
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
        createJobButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

        JButton viewPeopleButton = new JButton("View People");
        viewPeopleButton.setBackground(new Color(238, 192, 68));
        viewPeopleButton.setForeground(Color.BLACK);
        viewPeopleButton.setBorder(buttonBorder);
        viewPeopleButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

        JButton viewBusinessButton = new JButton("View Business");
        viewBusinessButton.setBackground(new Color(238, 192, 68));
        viewBusinessButton.setForeground(Color.BLACK);
        viewBusinessButton.setBorder(buttonBorder);
        viewBusinessButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

        JButton siftListButton = new JButton("Sift List");
        siftListButton.setBackground(new Color(238, 192, 68));
        siftListButton.setForeground(Color.BLACK);
        siftListButton.setBorder(buttonBorder);
        siftListButton.setPreferredSize(new Dimension(300, 50)); // Set the preferred size for the button

        //Create Card

        JPanel createCard = new JPanel();
        createCard.setLayout(new GridLayout(8, 2, 10, 10));
        createCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        createCard.setBackground(new Color(238, 192, 68));

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
        String industry = null;
        while (cursor.hasNext()) {
            Document business = cursor.next();
            // Read the desired fields from the business document
            email = business.getString("email");
            industry = business.getString("industry");
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

        //Create viewPeople card

        JPanel viewPeopleCard = new JPanel();
        viewPeopleCard.setLayout(new BorderLayout());
        viewPeopleCard.setBackground(new Color(238, 192, 68));

        JPanel viewPeopleMain = new JPanel();
        viewPeopleMain.setLayout(new BorderLayout());
        viewPeopleMain.setBackground(new Color(238, 192, 68));

        JPanel viewPeopleHeaderPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(238, 192, 68)); // Adjust the RGB values for different shades of gold
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        viewPeopleHeaderPanel.setLayout(new GridBagLayout());
        GridBagConstraints h = new GridBagConstraints();
        h.gridy = 0; h.gridx = 0;h.insets = new Insets(5, 5, 5, 5);

        JButton backButton3 = new JButton("Back to Main Menu");
        backButton3.setBackground(Color.BLACK);
        backButton3.setForeground(new Color(238, 192, 68));

        JButton addPeopleToSiftList = new JButton("Add People to SiftList");
        addPeopleToSiftList.setBackground(Color.BLACK);
        addPeopleToSiftList.setForeground(new Color(238, 192, 68));

        viewPeopleHeaderPanel.add(addPeopleToSiftList, h);
        h.gridx++;
        viewPeopleHeaderPanel.add(backButton3, h);

        List<Document> applicants = new ArrayList<>();

        MongoCursor<Document> cursor2 = connection.applicants.find().cursor();
        while (cursor2.hasNext()){
            applicants.add(cursor2.next());
        }
        cursor2.close();

        JPanel viewPeoplepanel = new JPanel(new GridBagLayout());
        viewPeoplepanel.setBackground(new Color(238, 192, 68));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel tutorialPanel = new JPanel(new BorderLayout());
        tutorialPanel.setBackground(new Color(238, 192, 68));
        tutorialPanel.setPreferredSize(new Dimension(450, 50));
        tutorialPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JLabel tutorialLabel1 = new JLabel("Each Panel contains info organized as Name, Company, Pay. Use the checkbox to add a job to your SiftList.");

        tutorialLabel1.setForeground(Color.BLACK);
        tutorialPanel.add(tutorialLabel1, BorderLayout.NORTH);
        viewPeoplepanel.add(tutorialPanel, gbc);
        gbc.gridy++;

        for (Document person : applicants) {
            JPanel personPanel = new JPanel(new BorderLayout());
            personPanel.setBackground(new Color(238, 192, 68));
            personPanel.setPreferredSize(new Dimension(700, 50));
            personPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

            List<String> skillsarr = (List<String>) person.get("skills");
            String skills = String.join(", ", skillsarr);

            // Create labels for job details
            JLabel info = new JLabel(person.getString("name") + ", " + skills + ", $" + person.get("salary") + "/hr" );
            info.setForeground(Color.BLACK);
            // Create check mark button
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(new Color(238, 192, 68));

            checkBox.addActionListener(e -> {
                // Handle check mark button click event
                // Perform the necessary actions when the button is clicked
                if(checkBox.isSelected()) {
                    allChecked.add(person.get("_id"));
                }else{
                    allChecked.remove(person.get("_id"));
                }
            });

            // Add labels and check mark button to the job panel

            personPanel.add(info, BorderLayout.CENTER);
            personPanel.add(checkBox, BorderLayout.EAST);

            // Add job panel to the main panel
            viewPeoplepanel.add(personPanel, gbc);
            gbc.gridy++;
        }

        // Create scroll pane and add the main panel to it
        JScrollPane scrollPane = new JScrollPane(viewPeoplepanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(238, 192, 68));

        viewPeopleCard.add(viewPeopleHeaderPanel, BorderLayout.NORTH);
        viewPeopleCard.add(scrollPane, BorderLayout.CENTER);

        ///////////////SIFTLIST///////////////////////////
        JPanel siftMainPanel = new JPanel();
        siftMainPanel.setLayout(new BorderLayout());

        JPanel siftcards = new JPanel();
        CardLayout layout = new CardLayout();
        siftcards.setLayout(layout);

        JPanel siftbuttonPanel = new JPanel();
        siftbuttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0; b.gridy = 0; b.insets = new Insets(0,5,0,5); b.fill = GridBagConstraints.HORIZONTAL;
        siftbuttonPanel.setBackground(new Color(238, 192, 68));
        siftbuttonPanel.setPreferredSize(new Dimension(getWidth(), 75));
        siftbuttonPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));

        JButton next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(new Color(238, 192, 68));
        next.setPreferredSize(new Dimension(100,50));

        JButton prev = new JButton("Previous");
        prev.setBackground(Color.BLACK);
        prev.setForeground(new Color(238, 192, 68));
        prev.setPreferredSize(new Dimension(100,50));

        JButton backButton4 = new JButton("Back to Main Menu");
        backButton4.setBackground(Color.BLACK);
        backButton4.setForeground(new Color(238, 192, 68));
        backButton4.setPreferredSize(new Dimension(150,50));

        siftbuttonPanel.add(prev, b);
        b.gridx++;
        siftbuttonPanel.add(backButton4, b);
        b.gridx++;
        siftbuttonPanel.add(next, b);

        siftMainPanel.add(siftcards, BorderLayout.CENTER);
        siftMainPanel.add(siftbuttonPanel,BorderLayout.SOUTH);


        cards.add(buttonPanel, "buttons");
        cards.add(createCard, "create");
        cards.add(viewPeopleCard, "viewPeople");
        cards.add(viewBusinessCard, "viewBusiness");
        cards.add(siftMainPanel, "siftList");

        //Button Listeners
        createJobButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "create");
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
        viewPeopleButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "viewPeople");
        });
        viewPeopleButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                viewPeopleButton.setBackground(Color.BLACK);
                viewPeopleButton.setForeground(new Color(238, 192, 68));
            }

            @Override
            public void focusLost(FocusEvent e) {
                viewPeopleButton.setBackground(new Color(238, 192, 68));
                viewPeopleButton.setForeground(Color.BLACK);
            }
        });
        viewBusinessButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "viewBusiness");
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
        submitButton.addActionListener(e -> {
            String jobTitle = jobTitleField.getText();
            String jobDescription = jobDescriptionField.getText();
            String skills = skillsField.getText();
            String[] skillsarr = skills.split(",");
            String locations = locationsField.getText();
            String[] locationsarr = locations.split(",");
            String salary = salaryField.getText();

            connection.registerJob(name, jobTitle, jobDescription, skillsarr, locationsarr, Double.parseDouble(salary), email);

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

        backButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            cardLayout.previous(cards);
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
        submitButton2.addActionListener(e -> {
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
//            dispose();
//            MainBusinessUI mainBusinessUI = new MainBusinessUI(name, connection);
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

        addPeopleToSiftList.addActionListener(e -> siftList.addAll(allChecked));
        addPeopleToSiftList.addFocusListener(new FocusListener() {
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

        siftListButton.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            CardLayout cl = (CardLayout) (cards.getLayout());
            for(Object person : siftList){
                JPanel personPanel = new JPanel();
                personPanel.setBackground(new Color(238, 192, 68));
                personPanel.setLayout(new GridBagLayout());
                GridBagConstraints c1 = new GridBagConstraints();
                c1.gridx = 0; c1.gridy = 0; c1.fill = GridBagConstraints.HORIZONTAL;
                Document doc2 = connection.applicants.find(eq("_id", person)).first();
                String skills = String.join(",",(List<String>)doc2.get("skills"));
                String locationsText = String.join(",",(List<String>)doc2.get("locations"));

                JLabel nameL = new JLabel("Name:"); JLabel personName = new JLabel(doc2.getString("name"));

                JLabel emailL = new JLabel("Email:"); JLabel email = new JLabel(doc2.getString("email"));

                JLabel skillsL = new JLabel("Skills:"); JLabel skillsText = new JLabel(skills);

                JLabel locationsL = new JLabel("Preferred Location(s):"); JLabel locations = new JLabel(locationsText);

                JLabel payL = new JLabel("Expected Pay:"); JLabel pay = new JLabel(doc2.get("salary") + " $/hr");

                JButton resumeDownload = new JButton("Download Resume");
                resumeDownload.setBackground(Color.BLACK);
                resumeDownload.setForeground(new Color(238, 192, 68));

                Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                Font headerFont = new Font("Roboto", Font.BOLD, 28).deriveFont(fontAttributes);
                Font regular = new Font("Roboto", Font.PLAIN, 28);

                nameL.setFont(headerFont);
                nameL.setForeground(Color.BLACK);
                personName.setFont(regular);
                personName.setForeground(Color.BLACK);
                skillsL.setFont(headerFont);
                skillsL.setForeground(Color.BLACK);
                skillsText.setFont(regular);
                skillsText.setForeground(Color.BLACK);
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

                personPanel.add(nameL, c1);
                c1.gridx++;
                personPanel.add(personName, c1);
                c1.gridy++; c1.gridx--;
                personPanel.add(skillsL, c1);
                c1.gridx++;
                personPanel.add(skillsText, c1);
                c1.gridy++; c1.gridx--;
                personPanel.add(locationsL, c1);
                c1.gridx++;
                personPanel.add(locations, c1);
                c1.gridy++; c1.gridx--;
                personPanel.add(payL, c1);
                c1.gridx++;
                personPanel.add(pay, c1);
                c1.gridy++; c1.gridx--;
                personPanel.add(emailL, c1);
                c1.gridx++;
                personPanel.add(email, c1);
                c1.gridy++;c1.gridx--;c1.gridwidth = 2;
                personPanel.add(resumeDownload, c1);

                resumeDownload.addActionListener(g -> {
                    try {
                        String userHome = System.getProperty("user.home");
                        String downloadsPath = Paths.get(userHome, "Downloads", name + "_resume.pdf").toString();
                        FileDownloadController.saveDecodedBytesToFile(connection.getByteData(person), downloadsPath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                siftcards.add(personPanel);
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
        backButton4.addActionListener(e -> {
            // Handle job seeker button click event
            // Perform the necessary actions when the user indicates they are seeking a job
            // For example, navigate to the job seeker section of your application
            cardLayout.show(cards, "buttons");
        });
        backButton4.addFocusListener(new FocusListener() {
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
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.insets = new Insets(20, 0, 40, 0);
        buttonPanel.add(createJobButton, c);
        c.gridy = 1;
        buttonPanel.add(viewPeopleButton,c);
        c.gridy = 2;
        buttonPanel.add(viewBusinessButton,c);
        c.gridy = 3;
        buttonPanel.add(siftListButton,c);

        mainPanel.add(cards, BorderLayout.CENTER);

        setContentPane(mainPanel);

        setVisible(true);
    }

}
