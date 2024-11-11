package com.rosoa0475.testingstudy;

import com.rosoa0475.testingstudy.models.CollegeStudent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class TestingStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingStudyApplication.class, args);
    }

    @Bean(name = "collegeStudent")
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

}
