package com.company.nsharova.model.dao;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.model.sql.StatementInsertion;

import javax.sql.DataSource;
import java.sql.*;

public class CourseDao extends AbstractJdbcDao<Course> {

  private static final String SQL_CREATE_COURSE = "INSERT INTO electives.courses "
      + "(name,description,duration,ownerId,level) "
      + "VALUES (?,?,?,?,?)";
  private static final String SQL_REMOVE_COURSE =
      "DELETE FROM electives.courses WHERE id = ?";
  private static final String SQL_GET_ALL_COURSES =
      "SELECT * FROM electives.courses";

  private static final String SQL_GET_COURSE_BY_ID =
          "SELECT * FROM electives.courses WHERE id = ?";

  private static final String SQL_GET_COURSE_BY_NAME =
          "SELECT * FROM electives.courses where name = ?";

  private static final String SQL_GET_COURSE_USER_ID =
          "SELECT * FROM electives.courses where name = ?";

  public CourseDao(
      DataSource dataSource,
      Extractor<Course, ResultSet> extractor,
      StatementInsertion<Course> insertion) {
    super(dataSource, extractor, insertion);
  }

  @Override
  protected String findAllQuery() {
    return SQL_GET_ALL_COURSES;
  }

  @Override
  protected String createQuery() {
    return SQL_CREATE_COURSE;
  }

  @Override
  protected String findByIdQuery() {
    return SQL_GET_COURSE_BY_ID;
  }

  @Override
  protected String findByNameQuery() {
    return SQL_GET_COURSE_BY_NAME;
  }

  @Override
  protected String removeQuery() {
    return SQL_REMOVE_COURSE;
  }
/*
  public List<Course> getAllCoursesByUserId(int userId) {
    List<Course> courses = new ArrayList<>();
    Connection connection = null;
    try {
      connection = createConnection();

      PreparedStatement preparedStatement = connection.prepareStatement()) {
        ResultSet rs = statement.executeQuery(findAllQuery());
        while (rs.next()) {
          courses.add(extractor.extractFrom(rs));
        }
      }
    } catch (SQLException ex) {
      //throw new DaoException("Cannot find all users", ex);
      //throw new DaoException("Cannot commit transaction", ex);
    } finally {
      close(connection);
    }
    return entries;

 */
}
