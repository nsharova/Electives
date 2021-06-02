package com.company.nsharova.controller;

import com.company.nsharova.dao.CourseDao;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.extractor.impl.CourseExtractor;
import com.company.nsharova.extractor.impl.CourseFromRsExtractor;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.service.CourseService;
import com.company.nsharova.sql.CreateCourseStatementPopulator;
import com.company.nsharova.sql.StatementPopulator;
import com.company.nsharova.validator.CourseValidator;
import com.company.nsharova.validator.Validator;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

@WebListener
public class ContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    // data source
    DataSource dataSource = dataSource();

    // extractor
    Extractor<Course, HttpServletRequest> courseExtractor = new CourseExtractor();
    Validator<Course> courseValidator = new CourseValidator();

    // dao
    Extractor<Course, ResultSet> courseFromRsExtractor = new CourseFromRsExtractor();
    StatementPopulator<Course> coursePopulator = new CreateCourseStatementPopulator();
    CourseDao courseDao = new CourseDao(dataSource, courseFromRsExtractor, coursePopulator);

    // service
    CourseService courseService = new CourseService(courseDao);

    // commands
    AddCourseCommand addCourseCommand = new AddCourseCommand(
        courseExtractor, courseValidator, courseService);
    CommandContainer.register(addCourseCommand);
    GetCoursesCommand getCoursesCommand = new GetCoursesCommand(
        courseService);
    CommandContainer.register(getCoursesCommand);
    DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(
        courseService);
    CommandContainer.register(deleteCourseCommand);
  }

  private DataSource dataSource() {
    String dbDriver = "com.mysql.cj.jdbc.Driver";
    try {
      Class.forName(dbDriver);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException();
      //throw new DaoException("Cannot initialize data source", ex);
    }

    BasicDataSource dataSource = new BasicDataSource();

    dataSource.setDriverClassName(dbDriver);
    dataSource.setUrl("jdbc:mysql://localhost:3306/electives");
    dataSource.setUsername("root");
    dataSource.setPassword("Fqdtyuj4808)");

    return dataSource;
  }
}
