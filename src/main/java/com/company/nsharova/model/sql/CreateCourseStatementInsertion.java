package com.company.nsharova.model.sql;

import com.company.nsharova.model.entity.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateCourseStatementInsertion implements StatementInsertion<Course> {

  @Override
  public void toInsert(PreparedStatement statement, Course entity) throws SQLException {
    int index = 1;
    statement.setString(index++, entity.getName());
    statement.setString(index++, entity.getDescription());
    statement.setInt(index++, entity.getDuration());
    statement.setInt(index++, entity.getOwnerId());
    statement.setInt(index++, entity.getUserLevel());
    statement.executeUpdate();
    try (ResultSet keys = statement.getGeneratedKeys()) {
      if (keys.next()) {
        entity.setId(keys.getInt(1));
      } else {
        throw new SQLException("Cannot obtain generated id");
      }
    }
  }
}
