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
        Map<String, String> urlMap = new HashMap<>();

        //BufferedReader is same principle as Scanner, only faster and better in line reading (and larger files)
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            //Here we put a value inside String variable 'line' by reading the file, and if there is a line, we iterate through the loop.
            while ( (line = reader.readLine()) != null) {

                //If we have a line that doesnt start with # (used for comments in the file) AND the line contains =, then we split that line
                if (!line.startsWith("#") && line.contains("=")) {

                    //And ofcourse we split it at = because we want the jdbc:mysql://... as the value in the connect method
                    String[] parts = line.split("=", 2);
                    //limit: 2 is because we have two = in the line, but we need everything AFTER the first one, otherwise we would have
                    //3 splits, where REQUIRED was the last one, we dont want that.

                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    //Puts the "AIVEN_DB_URL" into key, and the URL into value, then puts that into the HashMap, urlMap
                    urlMap.put(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load .env file: " + e.getMessage());
        }
        return urlMap;
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