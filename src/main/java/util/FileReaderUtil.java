package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {

    public static InputStream getFileAsIOStream(String fileName) {
        InputStream ioStream = FileReaderUtil.class
                .getClassLoader()
                .getResourceAsStream(fileName);
        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public static List<String> getFileContentAsList(String fileName) throws IOException, URISyntaxException {
        URL resource = FileReaderUtil.class
                        .getClassLoader()
                        .getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        Path path = Paths.get(resource.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

}
