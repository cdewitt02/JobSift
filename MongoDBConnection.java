import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Base64;
import java.util.Arrays;
import java.util.List;

public class MongoDBConnection {

    public MongoClient mongoClient;
    public MongoDatabase database;
    public MongoCollection<Document> businesses;
    public MongoCollection<Document> applicants;
    public MongoCollection<Document> jobs;
    public MongoDBConnection(String connectionString, String databaseName) {
        // Create a MongoDB client
        MongoClientURI uri = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(uri);

        // Access the database
        database = mongoClient.getDatabase(databaseName);

        //Get Collections
        businesses = database.getCollection("businesses");
        applicants = database.getCollection("applicants");
        jobs = database.getCollection("jobs");
    }
    public void registerBusiness(String name, String email, String industry, String password) {

        password = PasswordEncryption.encryptPassword(password);
        // Create a new business document
        Document business = new Document("name", name)
                .append("email", email)
                .append("industry", industry)
                .append("password", password);

        // Insert the document into the collection
        businesses.insertOne(business);

        System.out.println("Business registered successfully.");
    }
    public void registerApplicant(String name, String password, String email, String[] skills, String[] locations, Double salary, String resume) {

        List<String> skills_list = Arrays.asList(skills);
        List<String> locations_list = Arrays.asList(locations);

        password = PasswordEncryption.encryptPassword(password);

        // Create a new business document
        Document applicant = new Document("name", name)
                .append("password", password)
                .append("email", email)
                .append("skills", skills_list)
                .append("locations", locations_list)
                .append("salary", salary)
                .append("resume", resume);


        // Insert the document into the collection
        applicants.insertOne(applicant);

        System.out.println("Applicant registered successfully.");
    }
    public void registerJob(String company, String jobTitle, String jobDescription, String[] requiredSkills, String[] preferredLocations, double salary, String contactEmail) {
        List<String> skills_list = Arrays.asList(requiredSkills);
        List<String> locations_list = Arrays.asList(preferredLocations);

        Document job = new Document("company", company)
                .append("jobTitle", jobTitle)
                .append("jobDescription", jobDescription)
                .append("requiredSkills", skills_list)
                .append("locations", locations_list)
                .append("pay", salary)
                .append("contactEmail", contactEmail);
        jobs.insertOne(job);
    }
    public byte[] getByteData(Object docID){
        Document filter = new Document("_id", docID);
        Document doc = applicants.find(filter).first();
        String resume = (String)doc.get("resume");
        return Base64.getDecoder().decode(resume);
    }
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
