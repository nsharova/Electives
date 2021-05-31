package com.company.nsharova.model.dao;

import com.company.nsharova.model.dao.impl.JDBCDAOFactory;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;

    public abstract UserDAO createUserDao();
    public abstract CourseDAO createCourseDao();
    public abstract ThemeDAO createThemeDao();
    public abstract CourseAssigmentDAO createCourseAssigmentDao();

    public static DAOFactory getInstance(){
        if( daoFactory == null ) {
                    daoFactory = new JDBCDAOFactory();
        }
        return daoFactory;
    }
}
