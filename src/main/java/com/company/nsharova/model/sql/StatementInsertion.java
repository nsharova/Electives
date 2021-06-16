package com.company.nsharova.model.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInsertion<T> {
  void toInsert(PreparedStatement statement, T entity) throws SQLException;
}
