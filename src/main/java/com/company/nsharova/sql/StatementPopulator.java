package com.company.nsharova.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementPopulator<T> {
  void populate(PreparedStatement statement, T entity) throws SQLException;
}
