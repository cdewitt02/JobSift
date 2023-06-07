import java.io.File;
import java.util.Arrays;

public class Applicant {
    private String name;
    private String email;
    private File resume;
    private String[] skills;
    private String[] preferredLocations;
    private double expectedSalary;

    public Applicant(){
        this.name = null;
        this.email = null;
        this.resume = null;
        this.skills = null;
        this.preferredLocations = null;
        this.expectedSalary = -1;
    }
    public Applicant(String name, String email, File resume, String[] skills, String[] preferredLocations, double expectedSalary) {
        this.name = name;
        this.email = email;
        this.resume = resume;
        this.skills = skills;
        this.preferredLocations = preferredLocations;
        this.expectedSalary = expectedSalary;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public File getResume() {
        return resume;
    }

    public void setResume(File resume) {
        this.resume = resume;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getPreferredLocations() {
        return preferredLocations;
    }

    public void setPreferredLocations(String[] preferredLocations) {
        this.preferredLocations = preferredLocations;
    }

    public double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    // Override toString() method to provide a string representation of the JobSeeker object
    @Override
    public String toString() {
        return "JobSeeker{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", resume='" + resume + '\'' +
                ", skills=" + Arrays.toString(skills) +
                ", preferredLocations=" + Arrays.toString(preferredLocations) +
                ", expectedSalary=" + expectedSalary +
                '}';
    }
}
