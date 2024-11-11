package com.rosoa0475.testingstudy.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestMethodOrder(OrderAnnotation.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setUp() {
        //set up
        demoUtils = new DemoUtils();
    }

    @Test
    @Order(1)
    @EnabledOnJre(JRE.JAVA_17)
    void add() {
        //execute
        int actual = demoUtils.add(2, 4);

        //assert
        Assertions.assertEquals(6, actual, "2+4 must be 6");
        Assertions.assertNotEquals(7, actual, "2+4 must not be 7");
    }

    @Test
    @Order(2)
    @EnabledOnJre(JRE.JAVA_10)
    void checkNull() {
        //set up
        DemoUtils demoUtils = new DemoUtils();

        String string1 = null;
        String string2 = "choi";

        //execute
        Object obj1 = demoUtils.checkNull(string1);
        Object obj2 = demoUtils.checkNull(string2);

        //assert
        Assertions.assertNull(obj1, "Object must be null");
        Assertions.assertNotNull(obj2, "Object must not be null");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_20)
    void testSameAndNotSame() {
        //set up
        String str = "choi";

        //execute
        String str2 = demoUtils.getAcademy();
        String str3 = demoUtils.getAcademyDuplicate();

        //assert
        Assertions.assertSame(str2, str3, "Academy must be the same");
        Assertions.assertNotSame(str, str2, "Academy must not be the same");


    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_18)
    void isGreater() {
        //set up
        int n1 = 10;
        int n2 = 5;

        //execute
        boolean result1 = demoUtils.isGreater(n1, n2);
        boolean result2 = demoUtils.isGreater(n2, n1);

        //assert
        assertTrue(result1, "Should be greater than " + n1 + " and " + n2);
        assertFalse(result2, "Should be not greater than " + n1 + " and " + n2);
    }

    @Test
    @EnabledIfEnvironmentVariables({
        @EnabledIfEnvironmentVariable(named = "text", matches = "uncorrect"),
        @EnabledIfEnvironmentVariable(named = "test", matches = "correct"),
    })
    void testArrayEquals() {
        //set up
        String[] strings = {"A", "B", "C"};

        //execute
        String[] firstThreeSmallLettersOfAlphabet = demoUtils.getFirstThreeLettersOfAlphabet();

        //assert
        assertArrayEquals(strings, firstThreeSmallLettersOfAlphabet, "Should be same");
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    void testIterableEquals() {
        //set up
        List<String> list = List.of("choi", "jae", "hyuk");

        //execute
        List<String> name = demoUtils.getName();

        //assert
        assertIterableEquals(list, name, "Should be same");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testLinesMatch() {
        //set up
        List<String> list = List.of("choi", "jae", "hyuk");

        //execute
        List<String> name = demoUtils.getName();

        //assert
        assertLinesMatch(list, name, "Should be same");

    }

    @Test
    @EnabledOnOs(OS.MAC)
    void throwExceptionIfLessThanZero() {
        //set up
        int n1 = -1;
        int n2 = 1;

        //execute & assert
        assertThrows(Exception.class, () -> demoUtils.throwExceptionIfLessThanZero(n1));
        assertDoesNotThrow(() -> demoUtils.throwExceptionIfLessThanZero(n2));
    }

    @Test
    @Disabled("not test")
    void checkTimeout() {
        //set up
        long sec = 3;

        //execute & assert
        assertTimeoutPreemptively(Duration.ofSeconds(sec), () -> demoUtils.checkTimeout());
    }
}