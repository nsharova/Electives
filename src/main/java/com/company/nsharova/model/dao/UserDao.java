package com.company.nsharova.model.dao;

import com.company.nsharova.controller.command.impl.LoginCommand;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.extractor.impl.UserExtractor;
import com.company.nsharova.extractor.impl.UserFromRsExtractor;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.sql.StatementInsertion;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


}
