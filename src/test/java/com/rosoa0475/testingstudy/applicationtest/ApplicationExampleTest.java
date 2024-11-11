package com.rosoa0475.testingstudy.applicationtest;

import static org.junit.jupiter.api.Assertions.*;

import com.rosoa0475.testingstudy.TestingStudyApplication;
import com.rosoa0475.testingstudy.models.CollegeStudent;
import com.rosoa0475.testingstudy.models.StudentGrades;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = TestingStudyApplication.class)
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        count = count + 1;
        System.out.println(
            "Testing: " + appInfo + " which is " + appDescription + " Version " + appVersion
                + ". Execution of test method " + count);
        collegeStudent.setFirstname("Eric");
        collegeStudent.setLastname("Roby");
        collegeStudent.setEmailAddress("eric.roby@luv2code_school.com");
        studentGrades.setMathGradeResults(
            new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    void addGradeResultsForStudentGrades() {
        double expected = 353.25;

        assertEquals(expected,
            studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades()
                .getMathGradeResults()));
    }

    @Test
    void addGradeResultsForStudentGradesAssertNotEquals() {
        double expected = 0;

        Assertions.assertNotEquals(expected,
            studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades()
                .getMathGradeResults()));
    }

    @Test
    void isGradeGreaterStudentGrades() {
        assertTrue(studentGrades.isGradeGreater(90, 75), "Should be true");
    }

    @Test
    void isGradeGreaterStudentGradesAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(82, 90), "Should be false");
    }

    @Test
    void checkNullForStudentGrades() {
        assertNotNull(
            studentGrades.checkNull(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @Test
    void verifyStudentsArePrototypes() {
        CollegeStudent collegeStudentTwo = context.getBean("collegeStudent",CollegeStudent.class);

        assertNotSame(collegeStudent, collegeStudentTwo);
    }
}
