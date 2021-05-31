package com.company.nsharova.controller;

import com.company.nsharova.dto.CourseDto;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.service.CourseService;
import com.company.nsharova.validator.Validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add_course")
public class AddCourseController extends HttpServlet {

  private Extractor<CourseDto, HttpServletRequest> courseDtoExtractor;
  private Validator<CourseDto> courseValidator;
  private Extractor<Course, CourseDto> courseExtractor;
  private CourseService courseService;

  @Override
  public void init() {
    ServletContext context = getServletContext();
    courseDtoExtractor = (Extractor<CourseDto, HttpServletRequest>) context.getAttribute(
        "courseDtoExtractor");
    courseValidator = (Validator<CourseDto>) context.getAttribute(
        "courseDtoValidator");
    courseExtractor = (Extractor<Course, CourseDto>) context.getAttribute(
        "courseExtractor");
    courseService = (CourseService) context.getAttribute("courseService");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("WEB-INF/add_course.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String destination = "/add_course";

    Map<String, String> errors = new HashMap<>();

    CourseDto courseDto = courseDtoExtractor.extractFrom(request);
    courseValidator.validate(courseDto, errors);

    if (errors.isEmpty()) {
      Course validCourse = courseExtractor.extractFrom(courseDto);

      courseService.create(validCourse);

      destination = "/courses";
    } else {
      HttpSession session = request.getSession();
      session.setAttribute("errors", errors);

      session.setAttribute("tempCourse", courseDto);
    }

    String path = "/final-project" + destination;
    response.sendRedirect(path);
  }
}
