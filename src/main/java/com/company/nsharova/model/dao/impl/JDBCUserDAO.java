package com.company.nsharova.model.dao.impl;

import com.company.nsharova.model.dao.UserDAO;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDAO implements UserDAO {
    private Connection connection;


    public JDBCUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Course> curses = new HashMap<>();


            return null;
        }
}
