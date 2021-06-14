package com.company.nsharova.extractor.impl;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Theme;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeFromRsExtractor implements Extractor<Theme, ResultSet>{
    @Override
    public Theme extractFrom(ResultSet rs) {
        Theme theme = new Theme();

        try {
            theme.setId(rs.getInt("id"));
            theme.setName(rs.getString("name"));
        } catch (SQLException ex) {
            throw new RuntimeException();
            //throw new DaoException("Cannot extract user from result set", ex);
        }
        return theme;
    }
}
