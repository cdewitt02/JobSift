import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPersonUI extends JFrame {
    private DefaultListModel<String> jobListModel;
    private JList<String> jobList;

    public MainPersonUI(String name, MongoDBConnection connection) {
        setTitle("Main Person UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(238, 192, 68));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 50));

        // Create the label for the header
        ImageIcon icon = new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logoSmall.png"); // Replace with the path to your left image file
        setIconImage(new ImageIcon("C:\\Users\\charl\\Documents\\GitHub\\JobSift\\resources\\JobSift_logo.png").getImage());

        JLabel leftImageLabel = new JLabel(icon);
        JLabel rightImageLabel = new JLabel(icon);
        JLabel headerLabel = new JLabel("Job Feed");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(leftImageLabel, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(rightImageLabel, BorderLayout.EAST);

        // Create the job list
        jobListModel = new DefaultListModel<>();
        jobList = new JList<>(jobListModel);
        jobList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jobList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create the scroll pane for the job list
        JScrollPane scrollPane = new JScrollPane(jobList);

        // Add the scroll pane to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        setVisible(true);
    }
    public void addJob(String jobTitle) {
        jobListModel.addElement(jobTitle);
    }

    public static void main(String[] args) {
        // Create an instance of the MainPersonUI class
        MongoDBConnection connection = new MongoDBConnection("mongodb://localhost:27017", "JobSiftDB");

        MainPersonUI mainPersonUI = new MainPersonUI("Charlie", connection);

        // Add some sample jobs



    }
}

