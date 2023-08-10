import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Base64;

public class FileDownloadController {
//    public static void main(String[] args) {
//        String base64Data = "YOUR_BASE64_ENCODED_DATA_HERE"; // Replace with your actual Base64 data
//        String savePath = "decoded_file.pdf"; // Replace with the desired save path
//
//        try {
//            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
//            saveDecodedBytesToFile(decodedBytes, savePath);
//            System.out.println("File created successfully.");
//        } catch (IOException e) {
//            System.err.println("Error while creating the file: " + e.getMessage());
//        }
//    }

    public static void saveDecodedBytesToFile(byte[] data, String savePath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
            outputStream.write(data);
        }
    }
}
