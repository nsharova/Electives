package com.company.nsharova.model.dao;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.extractor.impl.UserFromRsExtractor;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.sql.StatementInsertion;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractJdbcDao<User> {
    private static final Logger logger = Logger.getLogger(UserDao.class);

    private static final String SQL_CREATE_USER = "INSERT INTO electives.users "
            + "(login,password,firstName,lastName,role) "
            + "VALUES (?,?,?,?,?)";
    private static final String SQL_REMOVE_USER =
            "DELETE FROM electives.users WHERE id = ?";
    private static final String SQL_GET_ALL_USERS =
            "SELECT * FROM electives.users";

    private static final String GET_USER_BY_ID = "SELECT * FROM electives.users " +
            "WHERE id = ?";

    private static final String GET_USER_BY_NAME = "SELECT * FROM electives.users " +
            "WHERE login = ?";

    private static final String GET_USERS_BY_ROLE = "SELECT * FROM electives.users " +
            "WHERE role = ?";

    public UserDao(
            DataSource dataSource,
            Extractor<User, ResultSet> extractor,
            StatementInsertion<User> insertion) {
        super(dataSource, extractor, insertion);
    }

    @Override
    protected String findAllQuery() {
        return SQL_GET_ALL_USERS;
    }

    @Override
    protected String createQuery() {
        return SQL_CREATE_USER;
    }

    @Override
    protected String findByIdQuery() { return GET_USER_BY_ID; };

    @Override
    protected String findByNameQuery() { return GET_USER_BY_NAME; };

    @Override
    protected String removeQuery() {
        return SQL_REMOVE_USER;
    }


    public List<User> findUsersByRole(int role) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_BY_ROLE)) {
                preparedStatement.setInt(1, role);
                ResultSet resultSet = preparedStatement.executeQuery();
                Extractor<User,ResultSet>  userFromRsExtractor = new UserFromRsExtractor();
                while (resultSet.next()) {
                    users.add(userFromRsExtractor.extractFrom(resultSet));
                }
            }
        } catch (SQLException ex) {
            //throw new DaoException("Cannot find all users", ex);
            //throw new DaoException("Cannot commit transaction", ex);
        } finally {
            close(connection);
        }
        return users;
    }

}
