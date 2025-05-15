package io.github.mortenjenne.fridgechef.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseWriterTest {

    @Test
    void createAccount() {
        //Tests for account creation if email is not in use

        //Arrange
        DatabaseWriter dbWriter = new DatabaseWriter();
        boolean expected = true;

        //Act
        boolean actual = dbWriter.createAccount("toby@chef.com","tadmin","tobyChef123!");

        //Assert
        assertEquals(expected,actual);
    }
}