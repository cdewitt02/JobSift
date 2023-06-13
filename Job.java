public class Job {
    private String company;
    private String jobTitle;
    private String jobDescription;
    private String[] requiredSkills;
    private String[] locations;
    private double expectedPay;

    public Job(){
        this.company = null;
        this.jobTitle = null;
        this.jobDescription = null;
        this.requiredSkills = null;
        this.locations = null;
        this.expectedPay = -1;
    }
    public Job(String company, String jobTitle, String jobDescription, String[] requiredSkills, String[] preferredLocations, double salary){
        this.company = company;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.requiredSkills = requiredSkills;
        this.locations = preferredLocations;
        this.expectedPay = salary;
    }
}
