package com.company.nsharova.dao;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.sql.StatementPopulator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractJdbcDao<T> {

  protected final DataSource dataSource;
  private final Extractor<T, ResultSet> extractor;
  private final StatementPopulator<T> populator;

  protected abstract String createQuery();
  protected abstract String readQuery();
  protected abstract String removeQuery();
  protected abstract String findAllQuery();

  public void create(T entity) {
    Connection connection = null;
    try {
      connection = createConnection();

      try (PreparedStatement preparedStatement = connection.prepareStatement(
          createQuery(), Statement.RETURN_GENERATED_KEYS)) {
        System.err.println(entity);
        populator.populate(preparedStatement, entity);
      } catch (SQLException ex) {
        //throw new DaoException("Cannot create user", ex);
      }

      connection.commit();
    } catch (SQLException ex) {
      rollback(connection);
      //throw new DaoException("Cannot commit transaction", ex);
    } finally {
      close(connection);
    }
  }

  public T read(Integer id) {
    T entity = null;
    Connection connection = null;
    try {
      connection = createConnection();

      try (PreparedStatement preparedStatement = connection
          .prepareStatement(readQuery())) {
        preparedStatement.setString(1, id.toString());
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          if (resultSet.next()) {
            entity = extractor.extractFrom(resultSet);
          }
        }
      } catch (SQLException ex) {
        //throw new DaoException("Cannot find user by given login", ex);
      }

      connection.commit();
    } catch (SQLException ex) {
      rollback(connection);
      //throw new DaoException("Cannot commit transaction", ex);
    } finally {
      close(connection);
    }
    return entity;
  }

  public boolean remove(Integer id) {
    Connection connection = null;
    try {
      connection = createConnection();

      try (PreparedStatement preparedStatement = connection
          .prepareStatement(removeQuery())) {
        preparedStatement.setString(1, id.toString());
        System.err.println(preparedStatement);
        preparedStatement.executeUpdate();
        System.err.println("removed");
        return true;
      } catch (SQLException ex) {
        System.err.println(ex);
        //throw new DaoException("Cannot remove user", ex);
      }

      connection.commit();
    } catch (SQLException ex) {
      System.err.println(ex);
      rollback(connection);
      //throw new DaoException("Cannot commit transaction", ex);
    } finally {
      close(connection);
    }

    return false;
  }

  public List<T> findAll() {
    List<T> entries = new ArrayList<>();
    Connection connection = null;
    try {
      connection = dataSource.getConnection();

      try (Statement statement = connection.createStatement()) {
        try (ResultSet rs = statement.executeQuery(findAllQuery())) {
          while (rs.next()) {
            entries.add(extractor.extractFrom(rs));
          }
        }
      } catch (SQLException ex) {
        //throw new DaoException("Cannot find all users", ex);
      }

      connection.commit();
    } catch (SQLException ex) {
      rollback(connection);
      //throw new DaoException("Cannot commit transaction", ex);
    } finally {
      close(connection);
    }
    return entries;
  }

  protected Connection createConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException ex) {
      throw new RuntimeException();
      //throw new DaoException("Cannot obtain connection from pull", ex);
    }
  }

  protected void rollback(Connection connection) {
    try {
      connection.rollback();
    } catch (SQLException ex) {
      //throw new DaoException("Cannot rollback transaction", ex);
    }
  }

  protected void close(Connection connection) {
    try {
      connection.close();
    } catch (Exception ex) {
      //throw new DaoException("Cannot close transaction", ex);
    }
  }
}
