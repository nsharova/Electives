package com.company.nsharova.controller;

import com.company.nsharova.service.CourseService;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.CharEncoding;

@WebServlet("/delete_course")
public class DeleteCourseController extends HttpServlet {

  private CourseService courseService;

  @Override
  public void init() {
    ServletContext context = getServletContext();
    courseService = (CourseService) context.getAttribute("courseService");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Integer courseId = Integer.valueOf(request.getParameter("courseId"));

    System.err.println(courseId);
    if (!courseService.removeCourseById(courseId)) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    response.sendRedirect("/final-project/courses");
  }
}
