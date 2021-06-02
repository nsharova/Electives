package com.company.nsharova.controller;

import com.company.nsharova.Paths;
import com.company.nsharova.service.CourseService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCourseCommand implements Command {

  private final CourseService courseService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    Integer courseId = Integer.valueOf(request.getParameter("courseId"));

    System.err.println(courseId);
    if (!courseService.removeCourseById(courseId)) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    return "/controller?command=courses";
  }

  @Override
  public String getName() {
    return "delete_course";
  }
}
