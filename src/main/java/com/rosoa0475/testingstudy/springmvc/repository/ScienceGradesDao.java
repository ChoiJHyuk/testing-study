package com.rosoa0475.testingstudy.springmvc.repository;

import com.rosoa0475.testingstudy.springmvc.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceGradesDao extends CrudRepository<ScienceGrade, Integer> {

    Iterable<ScienceGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);

}
