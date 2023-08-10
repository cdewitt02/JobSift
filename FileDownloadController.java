import java.io.FileOutputStream;
import java.io.IOException;

public class FileDownloadController {
    public static void saveDecodedBytesToFile(byte[] data, String savePath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
            outputStream.write(data);
        }
    }
}
