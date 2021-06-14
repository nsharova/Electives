package com.company.nsharova.sql;

import com.company.nsharova.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUserStatementInsertion implements StatementInsertion<User>{

    @Override
    public void toInsert(PreparedStatement statement, User entity) throws SQLException {
        int index = 1;
        statement.setString(index++, entity.getLogin());
        statement.setString(index++, entity.getPassword());
        statement.setString(index++, entity.getFirstName());
        statement.setString(index++, entity.getLastName());
        statement.setInt(index++, entity.getUserRole());
        statement.executeUpdate();
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (keys.next()) {
                entity.setId(keys.getInt(1));
            } else {
                throw new SQLException("Cannot obtain generated id");
            }
        }
    }
}
