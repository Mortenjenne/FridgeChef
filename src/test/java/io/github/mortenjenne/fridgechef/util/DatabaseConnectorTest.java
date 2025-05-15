package io.github.mortenjenne.fridgechef.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    @Test
    void connect() {
        DatabaseConnector db = new DatabaseConnector();
        db.connect();
    }
}