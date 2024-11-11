package com.rosoa0475.testingstudy.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestMethodOrder(OrderAnnotation.class)
class FizzBuzzTest {
    //1. 3으로 나뉘어 떨어지면 Fizz
    //2. 5로 나뉘어 떨어지면 Buzz
    //3. 3과 5로 나뉘어 떨어지면 FizzBuzz
    //4. 그 외의 경우는 숫자 그대로

    @Test
    @Order(1)
    void testForDivisibleByThree() {
        //set up
        String expected = "Fizz";

        //execute & assert
        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");
    }

    @Test
    @Order(1)
    void testForDivisibleByFive() {
        //set up
        String expected = "Buzz";

        //execute & assert
        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    @Test
    @Order(1)
    void testForDivisibleByThreeAndFive() {
        //set up
        String expected = "FizzBuzz";

        //execute & assert
        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    @Test
    @Order(1)
    void testForNotDivisibleByThreeOrFive() {
        //set up
        String expected = "1";

        //execute & assert
        assertEquals(expected, FizzBuzz.compute(1), "Should return 1");
    }

    @Order(1)
    @CsvFileSource(resources = "/small-test-data.csv", delimiterString = ",")
    @ParameterizedTest(name = "value={0}, expected={1}")
    void testSmallDataFile(int value, String expected) {

        //execute & assert
        assertEquals(expected, FizzBuzz.compute(value));
    }
}
