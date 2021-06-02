package com.company.nsharova.controller;

import com.company.nsharova.Paths;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.service.CourseService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCoursesCommand implements Command {

  private final CourseService courseService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    List<Course> courses = courseService.findAll();
    request.setAttribute("courses", courses);
    return Paths.COURSES_PAGE;
  }

  @Override
  public String getName() {
    return "courses";
  }
}
