package com.rosoa0475.testingstudy;

import com.rosoa0475.testingstudy.models.CollegeStudent;
import com.rosoa0475.testingstudy.springmvc.models.HistoryGrade;
import com.rosoa0475.testingstudy.springmvc.models.MathGrade;
import com.rosoa0475.testingstudy.springmvc.models.ScienceGrade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class TestingStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingStudyApplication.class, args);
    }

    @Bean
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

//    @Bean
//    @Scope(value = "prototype")
//    Grade getMathGrade(double grade) {
//        return new MathGrade(grade);
//    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("mathGrades")
    MathGrade getGrade() {
        return new MathGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("scienceGrades")
    ScienceGrade getScienceGrade() {
        return new ScienceGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("historyGrades")
    HistoryGrade getHistoryGrade() {
        return new HistoryGrade();
    }

}
