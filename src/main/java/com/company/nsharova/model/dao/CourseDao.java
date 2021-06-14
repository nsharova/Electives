package com.company.nsharova.model.dao;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.sql.StatementInsertion;

import javax.sql.DataSource;
import java.sql.ResultSet;

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


}
