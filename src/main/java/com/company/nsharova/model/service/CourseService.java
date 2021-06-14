package com.company.nsharova.model.service;

import com.company.nsharova.model.dao.CourseDao;
import com.company.nsharova.model.entity.Course;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CourseService {

  private final CourseDao courseDao;

  public List<Course> findAll() {
    return courseDao.findAll();
  }

  public void create(Course course) {
    System.err.println(course);
    courseDao.create(course);
  }

  public boolean removeCourseById(Integer courseId) {
    return courseDao.remove(courseId);
  }
}
