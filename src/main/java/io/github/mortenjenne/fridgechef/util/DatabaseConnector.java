package io.github.mortenjenne.fridgechef.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnector {
    protected Connection conn;

    private Map<String, String> loadEnvFile(String filepath) {
        Map<String, String> envVars = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#") && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    envVars.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load .env file: " + e.getMessage());
        }
        return envVars;
    }

    public void connect() {
        Map<String, String> env = loadEnvFile(".env");
        String dburl = env.get("AIVEN_DB_URL");

        if (dburl == null) {
            System.err.println("AIVEN_DB_URL not found in .env..");
            return;
        }

        try {
            conn = DriverManager.getConnection(dburl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}