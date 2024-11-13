package com.rosoa0475.testingstudy.springmvc.repository;

import com.rosoa0475.testingstudy.springmvc.models.MathGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathGradesDao extends CrudRepository<MathGrade, Integer> {
    Iterable<MathGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
