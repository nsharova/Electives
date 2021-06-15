package com.company.nsharova.controller;

import com.company.nsharova.controller.command.*;
import com.company.nsharova.controller.command.impl.*;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.extractor.impl.*;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.model.entity.Theme;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.service.CourseService;
import com.company.nsharova.model.service.ThemeService;
import com.company.nsharova.model.service.UserService;
import com.company.nsharova.sql.CreateCourseStatementInsertion;
import com.company.nsharova.sql.CreateThemeStatementInsertion;
import com.company.nsharova.sql.CreateUserStatementInsertion;
import com.company.nsharova.sql.StatementInsertion;
import com.company.nsharova.validator.CourseValidator;
import com.company.nsharova.validator.ThemeValidator;
import com.company.nsharova.validator.UserValidator;
import com.company.nsharova.validator.Validator;
import com.company.nsharova.model.dao.*;
import java.sql.ResultSet;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.h2.jdbcx.JdbcDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;


@WebListener
public class ContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    // data source
    DataSource dataSource = null;
    try {
      dataSource = dataSource();
    } catch (NamingException e) {
      e.printStackTrace();
    }

    // extractor
    Extractor<Course, HttpServletRequest> courseExtractor = new CourseExtractor();
    Validator<Course> courseValidator = new CourseValidator();

    Extractor<Theme, HttpServletRequest> themeExtractor = new ThemeExtractor();
    Validator<Theme> themeValidator = new ThemeValidator();

    Extractor<User, HttpServletRequest> userExtractor = new UserExtractor();
    Validator<User> userValidator = new UserValidator();

    // dao
    Extractor<Course, ResultSet> courseFromRsExtractor = new CourseFromRsExtractor();
    StatementInsertion<Course> courseInsertion = new CreateCourseStatementInsertion();
    CourseDao courseDao = new CourseDao(dataSource, courseFromRsExtractor, courseInsertion);

    Extractor<Theme, ResultSet> themeFromRsExtractor = new ThemeFromRsExtractor();
    StatementInsertion<Theme> themeInsertion = new CreateThemeStatementInsertion();
    ThemeDao themeDao = new ThemeDao(dataSource, themeFromRsExtractor, themeInsertion);

    Extractor<User, ResultSet> userFromRsExtractor = new UserFromRsExtractor();
    StatementInsertion<User> userInsertion = new CreateUserStatementInsertion();
    UserDao userDao = new UserDao(dataSource, userFromRsExtractor, userInsertion);

    // service
    CourseService courseService = new CourseService(courseDao);
    ThemeService themeService = new ThemeService(themeDao);
    UserService userService = new UserService(userDao);

    // commands
    LoginCommand loginCommand = new LoginCommand(userService);
    CommandContainer.register(loginCommand);
    LogOutCommand logOutCommand = new LogOutCommand();
    CommandContainer.register(logOutCommand);

    AddCourseCommand addCourseCommand = new AddCourseCommand(courseExtractor, courseValidator, courseService);
    CommandContainer.register(addCourseCommand);
    GetCoursesCommand getCoursesCommand = new GetCoursesCommand(courseService);
    CommandContainer.register(getCoursesCommand);
    DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(courseService);
    CommandContainer.register(deleteCourseCommand);

    AddThemeCommand addThemeCommand = new AddThemeCommand(themeExtractor, themeValidator, themeService);
    CommandContainer.register(addThemeCommand);
    GetThemesCommand getThemesCommand = new GetThemesCommand(themeService);
    CommandContainer.register(getThemesCommand);
    DeleteThemeCommand deleteThemeCommand = new DeleteThemeCommand(themeService);
    CommandContainer.register(deleteThemeCommand);

  }

  private DataSource dataSource() throws NamingException {

   String dbDriver = "com.mysql.cj.jdbc.Driver";
  //  String dbDriver = "org.h2.Driver";

    try {
      Class.forName(dbDriver);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException();
      //throw new DaoException("Cannot initialize data source", ex);
    }

    BasicDataSource dataSource = new BasicDataSource();

    //dataSource.setDriverClassName(dbDriver);
    dataSource.setUrl("jdbc:mysql://localhost:3306/electives");
    dataSource.setUrl("jdbc:h2:~/test");
    dataSource.setUsername("root");
    dataSource.setPassword("Fqdtyuj4808)");




    return dataSource;
  }
}
