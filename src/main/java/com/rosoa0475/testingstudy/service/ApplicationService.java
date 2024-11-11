package com.rosoa0475.testingstudy.service;

import com.rosoa0475.testingstudy.dao.ApplicationDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationDao applicationDao;

    public double addGradeResultsForSingleClass(List<Double> numbers) {
        return applicationDao.addGradeResultsForSingleClass(numbers);
    }

    public double findGradePointAverage (List<Double> grades ) {
        return applicationDao.findGradePointAverage(grades);
    }

    public Object checkNull(Object obj) {
        return applicationDao.checkNull(obj);
    }

}
