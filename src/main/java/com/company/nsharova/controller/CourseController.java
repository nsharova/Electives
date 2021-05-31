package com.company.nsharova.controller;

import com.company.nsharova.model.entity.Course;
import com.company.nsharova.service.CourseService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/courses")
public class CourseController extends HttpServlet {

  private CourseService courseService;

  @Override
  public void init() {
    ServletContext context = getServletContext();
    courseService = (CourseService) context.getAttribute("courseService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Course> courses = courseService.findAll();
    request.setAttribute("courses", courses);
    request.getRequestDispatcher("WEB-INF/courses.jsp").forward(request, response);
  }
}
