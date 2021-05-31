package com.company.nsharova.extractor.impl;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

public class CourseFromRsExtractor implements Extractor<Course, ResultSet> {

  @Override
  public Course extractFrom(ResultSet rs) {
    Course course = new Course();

    try {
      course.setId(rs.getInt("id"));
      course.setName(rs.getString("name"));
      course.setDescription(rs.getString("description"));
      course.setDuration(rs.getInt("duration"));
      course.setOwnerId(rs.getInt("ownerId"));
      course.setUserLevel(rs.getInt("level"));
    } catch (SQLException ex) {
      throw new RuntimeException();
      //throw new DaoException("Cannot extract user from result set", ex);
    }

    return course;
  }
}
