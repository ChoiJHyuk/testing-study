package com.rosoa0475.testingstudy.springmvc.repository;

import com.rosoa0475.testingstudy.springmvc.models.HistoryGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryGradesDao extends CrudRepository<HistoryGrade, Integer> {
    Iterable<HistoryGrade> findGradeByStudentId(int studentId);

    void deleteByStudentId(int studentId);
}
