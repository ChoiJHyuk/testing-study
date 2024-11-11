package com.rosoa0475.testingstudy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rosoa0475.testingstudy.dao.ApplicationDao;
import com.rosoa0475.testingstudy.models.CollegeStudent;
import com.rosoa0475.testingstudy.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    ApplicationDao applicationDao;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    void setUp() {
        collegeStudent.setFirstname("Eric");
        collegeStudent.setLastname("Bob");
        collegeStudent.setEmailAddress(
            collegeStudent.getFirstname() + " " + collegeStudent.getLastname());
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    void assertEqualsTestAddGrades() {
        Mockito.when(
                applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
            .thenReturn(100.0);

        double expected = 100.0;

        assertEquals(expected,
            applicationService.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults()));

        Mockito.verify(applicationDao, Mockito.times(1))
            .addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @Test
    void throwRuntimeException() {
        CollegeStudent nullStudent = context.getBean("collegeStudent", CollegeStudent.class);

        Mockito.when(applicationDao.checkNull((nullStudent))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> applicationService.checkNull((nullStudent)));

        Mockito.verify(applicationDao).checkNull((nullStudent));
    }

    @Test
    void stubbingConsecutiveCalls() {
        CollegeStudent nullStudent = context.getBean("collegeStudent", CollegeStudent.class);

        Mockito.when(applicationDao.checkNull((nullStudent))).thenThrow(RuntimeException.class)
            .thenReturn("Do not throw exception second time");

        assertThrows(RuntimeException.class, () -> applicationService.checkNull((nullStudent)));
        assertEquals("Do not throw exception second time", applicationService.checkNull((nullStudent)));

        Mockito.verify(applicationDao, Mockito.times(2)).checkNull((nullStudent));
    }
}