package com.company.nsharova.extractor.impl;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;
import javax.servlet.http.HttpServletRequest;

public class CourseExtractor implements Extractor<Course, HttpServletRequest> {

  @Override
  public Course extractFrom(HttpServletRequest request) {
    Course course = new Course();

    course.setName(request.getParameter("name"));
    course.setDescription(request.getParameter("description"));
    try {
      course.setDuration(Integer.valueOf(request.getParameter("duration")));
    } catch (NumberFormatException ex) {
    }
    try {
      course.setUserLevel(Integer.valueOf(request.getParameter("level")));
    } catch (NumberFormatException ex) {
    }
    course.setOwnerId(Integer.valueOf(request.getParameter("ownerId")));

    return course;
  }
}
