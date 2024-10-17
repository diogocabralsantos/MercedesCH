package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInput = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fileInput);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties file.");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}