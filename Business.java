import java.util.Arrays;

public class Business {
    private String name;
    private String email;
    private String jobTitle;
    private String jobDescription;
    private String[] requiredSkills;
    private String[] locations;
    private double expectedPay;
    public Business(){
        this.name = null;
        this.email = null;
        this.jobTitle = null;
        this.jobDescription = null;
        this.requiredSkills = null;
        this.locations = null;
        this.expectedPay = -1;
    }
    public Business(String name, String email, String jobTitle, String jobDescription, String[] requiredSkills, String[] preferredLocations, double salaryRange) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.requiredSkills = requiredSkills;
        this.locations = preferredLocations;
        this.expectedPay = salaryRange;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String[] requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public double getSalaryRange() {
        return expectedPay;
    }

    public void setSalaryRange(double salaryRange) {
        this.expectedPay = salaryRange;
    }

    // Override toString() method to provide a string representation of the Business object
    @Override
    public String toString() {
        return "Business{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", requiredSkills=" + Arrays.toString(requiredSkills) +
                ", preferredLocations=" + Arrays.toString(locations) +
                ", salaryRange=" + expectedPay +
                '}';
    }
}
