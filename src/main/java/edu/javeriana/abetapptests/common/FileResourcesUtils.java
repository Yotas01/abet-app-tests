package edu.javeriana.abetapptests.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileResourcesUtils {

    public FileResourcesUtils() {
    }

    public static String getResourceAsString(String classPath) throws URISyntaxException, IOException {
        return Files.readString(Path.of(Objects.requireNonNull(FileResourcesUtils.class.getClassLoader().getResource(classPath)).toURI()));
    }
}
