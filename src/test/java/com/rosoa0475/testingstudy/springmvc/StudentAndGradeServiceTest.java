package com.rosoa0475.testingstudy.springmvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rosoa0475.testingstudy.springmvc.models.CollegeStudent;
import com.rosoa0475.testingstudy.springmvc.models.GradebookCollegeStudent;
import com.rosoa0475.testingstudy.springmvc.models.HistoryGrade;
import com.rosoa0475.testingstudy.springmvc.models.MathGrade;
import com.rosoa0475.testingstudy.springmvc.models.ScienceGrade;
import com.rosoa0475.testingstudy.springmvc.repository.HistoryGradesDao;
import com.rosoa0475.testingstudy.springmvc.repository.MathGradesDao;
import com.rosoa0475.testingstudy.springmvc.repository.ScienceGradesDao;
import com.rosoa0475.testingstudy.springmvc.repository.StudentDao;
import com.rosoa0475.testingstudy.springmvc.service.StudentAndGradeService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class StudentAndGradeServiceTest {

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradesDao mathGradesDao;

    @Autowired
    private ScienceGradesDao scienceGradesDao;

    @Autowired
    private HistoryGradesDao historyGradesDao;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    void setUpDatabase() {
        jdbcTemplate.execute(sqlAddStudent);
        jdbcTemplate.execute(sqlAddMathGrade);
        jdbcTemplate.execute(sqlAddScienceGrade);
        jdbcTemplate.execute(sqlAddHistoryGrade);
    }

    @AfterEach
    void setupAfterTransaction() {
        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);
        jdbcTemplate.execute(sqlDeleteScienceGrade);
        jdbcTemplate.execute(sqlDeleteHistoryGrade);

    }

    @Test
    void createStudentService() {
        studentService.createStudent("Choi", "Jaehyuk", "rosoa0475@gmail.com");

        CollegeStudent student = studentDao.findByEmailAddress("rosoa0475@gmail.com");

        assertEquals("rosoa0475@gmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    void isStudentNullCheck() {

        assertTrue(studentService.checkIfStudentIsNull(1));

        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    void deleteStudentService() {

        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        Optional<MathGrade> deletedMathGrade = mathGradesDao.findById(2);
        Optional<ScienceGrade> deletedScienceGrade = scienceGradesDao.findById(2);
        Optional<HistoryGrade> deletedHistoryGrade = historyGradesDao.findById(2);

        assertTrue(deletedCollegeStudent.isPresent());
        assertTrue(deletedMathGrade.isPresent());
        assertTrue(deletedScienceGrade.isPresent());
        assertTrue(deletedHistoryGrade.isPresent());

        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);
        deletedMathGrade = mathGradesDao.findById(2);
        deletedScienceGrade = scienceGradesDao.findById(2);
        deletedHistoryGrade = historyGradesDao.findById(2);

        assertFalse(deletedCollegeStudent.isPresent());
        assertFalse(deletedMathGrade.isPresent());
        assertFalse(deletedScienceGrade.isPresent());
        assertFalse(deletedHistoryGrade.isPresent());
    }

    @Test
    @Sql("/insertData.sql")
    void getGradeBookService() {
        Iterable<CollegeStudent> iterableCollegeStudent = studentService.getGradeBook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        System.out.println(schoolName);

        for (CollegeStudent collegeStudent : iterableCollegeStudent) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(2, collegeStudents.size());
    }

    @Test
    void createGradeService() {
        assertTrue(studentService.createGrade(80.50, 1, "math"));
        assertTrue(studentService.createGrade(80.50, 1, "science"));
        assertTrue(studentService.createGrade(80.50, 1, "history"));

        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(1);

        assertTrue(((Collection) mathGrades).size() == 2);
        assertTrue(((Collection) scienceGrades).size() == 2);
        assertTrue(((Collection) historyGrades).size() == 2);
    }

    @Test
    void createGradeServiceReturnFalse() {
        assertFalse(studentService.createGrade(105, 1, "math"));
        assertFalse(studentService.createGrade(-5, 1, "math"));
        assertFalse(studentService.createGrade(50.60, 2, "math"));
        assertFalse(studentService.createGrade(80.50, 1, "another"));
    }

    @Test
    void deleteGradeService() {
        assertEquals(1, studentService.deleteGrade(2, "math"));
        assertEquals(1, studentService.deleteGrade(2, "science"));
        assertEquals(1, studentService.deleteGrade(2, "history"));
    }

    @Test
    void deleteGradeServiceReturnStudentIdOfZero() {
        assertEquals(0, studentService.deleteGrade(0, "science"));
        assertEquals(0, studentService.deleteGrade(1, "another"));
    }

    @Test
    void studentInformation() {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent);
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Eric", gradebookCollegeStudent.getFirstname());
        assertEquals("Roby", gradebookCollegeStudent.getLastname());
        assertEquals("asdasd@asdasd", gradebookCollegeStudent.getEmailAddress());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);
    }

    @Test
    void studentInformationServiceReturnNull() {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(0);

        assertNull(gradebookCollegeStudent);
    }
}
