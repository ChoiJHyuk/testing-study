package com.rosoa0475.testingstudy.springmvc.repository;

import com.rosoa0475.testingstudy.springmvc.models.CollegeStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<CollegeStudent, Integer> {

    CollegeStudent findByEmailAddress(String emailAddress);
}
