import com.google.gson.*;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
public class JSONCreator {
    public static void createApplicantJsonFile(String name, String email, File resume, String[] skills,
                                               String[] preferredLocations, double expectedSalary) {
        Applicant applicant = new Applicant(name, email, resume, skills, preferredLocations, expectedSalary);
        String filePath = "C:\\Users\\charl\\Documents\\GitHub\\JobSift\\ApplicantJSON\\" + name + ".json";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);

        try {
            FileWriter file = new FileWriter(filePath);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Applicant JSON file created successfully!");
    }

    public static void createBusinessJsonFile(String name, String email, String industry) {
        Business business = new Business(name, email, industry);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String filePath = "C:\\Users\\charl\\Documents\\GitHub\\JobSift\\BusinessJSON\\" + name + ".json";

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(business, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Business JSON file created successfully!");
    }

    public static void createJobJsonFile(String company, String jobTitle, String jobDescription, String[] requiredSkills,
                                         String[] locations, double pay) {
        Job job = new Job(company, jobTitle, jobDescription, requiredSkills, locations, pay);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String filePath = "C:\\Users\\charl\\Documents\\GitHub\\JobSift\\JobJSON\\" + company + "_" + jobTitle + ".json";

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(job, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Job JSON file created successfully!");
    }
}
//    private static class FileTypeAdapter extends TypeAdapter<File> {
//        @Override
//        public void write(JsonWriter out, File value) throws IOException {
//            if (value == null) {
//                out.nullValue();
//                return;
//            }
//            out.value(value.getPath());
//        }
//
//        @Override
//        public File read(JsonReader in) throws IOException {
//            if (in.peek() == JsonToken.NULL) {
//                in.nextNull();
//                return null;
//            }
//            String path = in.nextString();
//            return new File(path);
//        }
//    }
//
//    private static class FileTypeAdapterFactory implements TypeAdapterFactory {
//        public static TypeAdapterFactory create() {
//            return new FileTypeAdapterFactory();
//        }
//
//        @Override
//        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
//            TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
//            return new TypeAdapter<T>() {
//                @Override
//                public void write(JsonWriter out, T value) throws IOException {
//                    if (value instanceof File) {
//                        File file = (File) value;
//                        out.value(file.getAbsolutePath());
//                    } else {
//                        delegate.write(out, value);
//                    }
//                }
//
//                @Override
//                public T read(JsonReader in) throws IOException {
//                    if (in.peek() == JsonToken.STRING) {
//                        String path = in.nextString();
//                        return (T) new File(path);
//                    } else {
//                        return delegate.read(in);
//                    }
//                }
//            };
//        }
//    }
//}
//
