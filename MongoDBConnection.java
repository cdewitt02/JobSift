import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDBConnection {

    public MongoClient mongoClient;
    public MongoDatabase database;
    public MongoCollection<Document> businesses;
    public MongoCollection<Document> applicants;

    public MongoDBConnection(String connectionString, String databaseName) {
        // Create a MongoDB client
        MongoClientURI uri = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(uri);

        // Access the database
        database = mongoClient.getDatabase(databaseName);

        //Get Collections
        businesses = database.getCollection("businesses");
        applicants = database.getCollection("applicants");
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
    public void registerApplicant(String name, String password, String email, String[] skills, String[] locations, Double salary, File resume) {

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
                .append("resume", resume.getAbsolutePath());


        // Insert the document into the collection
        applicants.insertOne(applicant);

        System.out.println("Applicant registered successfully.");
    }
    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
