package io.github.mortenjenne.fridgechef.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {
    protected Connection conn;

    /**
     * Obtain a connection from the Hikari ConnectionPool singleton.
     * This replaces the previous .env/DriverManager based connect logic.
     */
    public void connect() {
        try {
            // ConnectionPool instance should be initialized elsewhere (e.g. in the main app)
            conn = ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("Error obtaining connection from pool: " + e.getMessage());
            conn = null;
        }
    }

    /**
     * Helper to close the connection held by this connector.
     * Call when finished with database operations to return the connection to the pool.
     */
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
            conn = null;
        }
    }
}