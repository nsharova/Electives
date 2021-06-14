package com.company.nsharova.sql;

import com.company.nsharova.model.entity.Course;
import com.company.nsharova.model.entity.Theme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateThemeStatementInsertion implements StatementInsertion<Theme>{
    @Override
    public void toInsert(PreparedStatement statement, Theme entity) throws SQLException {
        int index = 1;
        statement.setString(index++, entity.getName());
        System.err.println(statement);
        statement.executeUpdate();
        System.err.println(statement);
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (keys.next()) {
                entity.setId(keys.getInt(1));
            } else {
                throw new SQLException("Cannot obtain generated id");
            }
        }
    }
}
