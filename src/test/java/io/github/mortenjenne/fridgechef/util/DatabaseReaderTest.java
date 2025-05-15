package io.github.mortenjenne.fridgechef.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseReaderTest {

    @Test
    void accountLogin() {
        //Tests for succesfull account login (both email and password is correct)

        //Arrange
        DatabaseReader dbReader = new DatabaseReader();
        boolean expected = true;

        //Act
        boolean actual = dbReader.accountLogin("toby@chef.com","tobyChef123!");

        //Assert
        assertEquals(expected,actual);
        }
    }
