package io.github.mortenjenne.fridgechef.util;

import io.github.mortenjenne.fridgechef.model.Account;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseReaderTest {

    @Test
    void accountLogin() {
        //Tests for succesfull account login (both email and password is correct)

        //Arrange
        DatabaseReader dbReader = new DatabaseReader();
        boolean expected = true;

        //Act
        Account actual = dbReader.accountLogin("toby@chef.dk","tobyChef123!");

        //Assert
        assertNotNull(actual);
        }

    @Test
    void testAccountLoginNull() {
        //Tests for failed account login

        //Arrange
        DatabaseReader dbReader = new DatabaseReader();

        //Act
        Account actual = dbReader.accountLogin("wrong@email.dk","tobyChef123!");

        //Assert
        assertNull(actual);
    }


    @Test
    void getAccountIngredients() {
        //Test for getting ingredientID in fridge, prints the ingredientID list if successfull

        //Arrange
        DatabaseReader dbReader = new DatabaseReader();
        String email = "toby@chef.dk";

        Account userTest = dbReader.accountLogin("toby@chef.dk","tobyChef123!");

        List<Integer> expected = new ArrayList<>();
        expected.add(4542);
        expected.add(11282);
        expected.add(11352);
        expected.add(11529);
        expected.add(10028033);

        //Act
        List<Integer> actual = dbReader.getAccountIngredients(userTest.getAccountID());

        //Assert
        assertEquals(expected,actual);

    }
}