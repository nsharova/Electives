package com.company.nsharova.extractor.impl;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFromRsExtractor implements Extractor<User, ResultSet> {
    @Override
    public User extractFrom(ResultSet rs) {
        User user = new User();

        try {
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setUserRole(rs.getInt("role"));
            user.setLocked(rs.getBoolean("is_locked"));
        } catch (SQLException ex) {
            throw new RuntimeException();
            //throw new DaoException("Cannot extract user from result set", ex);
        }

        return user;
    }
}
