package com.company.nsharova.web;

import com.company.nsharova.dao.CourseDao;
import com.company.nsharova.dto.CourseDto;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.extractor.impl.CourseDtoExtractor;
import com.company.nsharova.extractor.impl.CourseExtractor;
import com.company.nsharova.extractor.impl.CourseFromRsExtractor;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.service.CourseService;
import com.company.nsharova.sql.CreateCourseStatementPopulator;
import com.company.nsharova.sql.StatementPopulator;
import com.company.nsharova.validator.CourseDtoValidator;
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
    // get context
    ServletContext servletContext = event.getServletContext();

    // data source
    DataSource dataSource = dataSource();

    // extractor
    Extractor<CourseDto, HttpServletRequest> courseDtoExtractor = new CourseDtoExtractor();
    servletContext.setAttribute("courseDtoExtractor", courseDtoExtractor);

    Validator<CourseDto> courseDtoValidator = new CourseDtoValidator();
    servletContext.setAttribute("courseDtoValidator", courseDtoValidator);

    Extractor<Course, CourseDto> courseExtractor = new CourseExtractor();
    servletContext.setAttribute("courseExtractor", courseExtractor);

    // dao
    Extractor<Course, ResultSet> courseFromRsExtractor = new CourseFromRsExtractor();
    StatementPopulator<Course> coursePopulator = new CreateCourseStatementPopulator();
    CourseDao courseDao = new CourseDao(dataSource, courseFromRsExtractor, coursePopulator);

    // service
    CourseService courseService = new CourseService(courseDao);
    servletContext.setAttribute("courseService", courseService);
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
    dataSource.setDefaultAutoCommit(false);

    return dataSource;
  }
}
