package com.company.nsharova.model.dao;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Theme;
import com.company.nsharova.sql.StatementInsertion;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThemeDao extends AbstractJdbcDao<Theme>{
    private static final String SQL_CREATE_THEME= "INSERT INTO electives.themes"
            + "(name) VALUES (?)";
    private static final String SQL_REMOVE_THEME =
            "DELETE FROM electives.themes WHERE id = ?";
    private static final String SQL_GET_ALL_THEMES =
            "SELECT * FROM electives.themes";
    private static final String SQL_GET_THEME_BY_ID =
            "SELECT * FROM electives.themes WHERE id = ?";

    private static final String SQL_GET_THEME_BY_COURSE_ID =
            "select distinct themes.name from electives.themes" +
                    "left join themes_courses tc on themes.id = tc.theme_id" +
                    "left join courses c on tc.course_id = c.id" +
                    "where c.id = ?";



    public ThemeDao(
            DataSource dataSource,
            Extractor<Theme, ResultSet> extractor,
            StatementInsertion<Theme> insertion) {
        super(dataSource, extractor, insertion);
    }

    @Override
    protected String findAllQuery() {
        return SQL_GET_ALL_THEMES;
    }

    @Override
    protected String createQuery() {
        return SQL_CREATE_THEME;
    }

    @Override
    protected String readQuery() {
        return SQL_GET_THEME_BY_ID;
    }

    @Override
    protected String removeQuery() {
        return SQL_REMOVE_THEME;
    }

    public List<Theme> findAllByCourseId(int courseId) {
        List<Theme> themes = new ArrayList<>();
        Extractor<Theme, ResultSet> extractor = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery(SQL_GET_THEME_BY_COURSE_ID)){
                    while (rs.next()) {
                        themes.add(extractor.extractFrom(rs));
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
        return themes;
    }
}
