import java.util.Arrays;

public class Business {
    private String name;
    private String email;

    private String industry;

    public Business(){
        this.name = null;
        this.email = null;
        this.industry = null;

    }
    public Business(String name, String email,String industry) {
        this.name = name;
        this.email = email;
        this.industry = industry;
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

    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }


    // Override toString() method to provide a string representation of the Business object
    @Override
    public String toString() {
        return "Business{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
