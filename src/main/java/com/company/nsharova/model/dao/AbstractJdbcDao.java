package com.company.nsharova.model.dao;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.sql.StatementInsertion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractJdbcDao<T> {

    protected final DataSource dataSource;
    private final Extractor<T, ResultSet> extractor;
    private final StatementInsertion<T> insertion;

    protected abstract String createQuery();

    protected abstract String findByIdQuery();

    protected abstract String findByNameQuery();

    protected abstract String removeQuery();

    protected abstract String findAllQuery();

    public void create(T entity) {
        Connection connection = null;
        try {
            connection = createConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    createQuery(), Statement.RETURN_GENERATED_KEYS)) {
                insertion.toInsert(preparedStatement, entity);
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

    public Optional<T> getById(Integer id) {
        T entity = null;
        Connection connection = null;
        try {
            connection = createConnection();

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(findByIdQuery())) {
                preparedStatement.setString(1, id.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        entity = extractor.extractFrom(resultSet);
                    }
                }
        } catch (SQLException ex) {
            //throw new DaoException("Cannot commit transaction", ex);
        } finally {
            close(connection);
        }
        return Optional.ofNullable(entity);
    }

    public Optional<T> getByName(String name) {
        T entity = null;
        Connection connection = null;
        try {
            connection = createConnection();

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(findByNameQuery())) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    entity = extractor.extractFrom(resultSet);
                }
            }
        } catch (SQLException ex) {
            //throw new DaoException("Cannot commit transaction", ex);
        } finally {
            close(connection);
        }
        return Optional.ofNullable(entity);
    }

    public boolean remove(Integer id) {
        Connection connection = null;
        try {
            connection = createConnection();

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(removeQuery())) {
                preparedStatement.setInt(1, id);
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
            System.out.println("CoNN: "+connection);

            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(findAllQuery());
                    while (rs.next()) {
                        entries.add(extractor.extractFrom(rs));
                    }
                }
            } catch (SQLException ex) {
                //throw new DaoException("Cannot find all users", ex);
            //throw new DaoException("Cannot commit transaction", ex);
        } finally {
            close(connection);
        }
        return entries;
    }

    protected Connection createConnection() {
        try {
            System.out.println("created connection succesful");
            return dataSource.getConnection();
        } catch (SQLException ex) {
            System.out.println("created connection failed");
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
