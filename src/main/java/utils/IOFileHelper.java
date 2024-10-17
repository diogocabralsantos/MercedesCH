package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IOFileHelper {

    // Method to write a string to a file
    public static void writeToFile(String filePath, String content) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to clear the content of the file
    public static void clearFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("Error clearing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
