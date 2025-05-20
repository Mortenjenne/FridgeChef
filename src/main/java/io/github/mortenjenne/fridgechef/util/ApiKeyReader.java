package io.github.mortenjenne.fridgechef.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApiKeyReader {

    public String loadApiKeys(String apiKeyName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ( (line = reader.readLine()) != null) {
                if (!line.startsWith("#") && line.contains(apiKeyName)) {
                    String[] parts = line.split("=");

                    String value = parts[1].trim();
                    return value;
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load .env file: " + e.getMessage());
        }
        return null;
    }
}
