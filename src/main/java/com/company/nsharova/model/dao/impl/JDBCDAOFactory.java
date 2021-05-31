package com.company.nsharova.model.dao.impl;

import com.company.nsharova.model.dao.CourseDAO;
import com.company.nsharova.model.dao.DAOFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JDBCDAOFactory extends DAOFactory {
    private static final Logger logger = LogManager.getLogger();

    private CourseDAO courseDAOD = new JDBCCourseDAO();
    private FacultyDao facultyDao = new FacultyDatabaseDao();
    private SpecialityDao specialityDao = new SpecialityDatabaseDao();
    private EducationalProgramDao programDao = new EducationalProgramDatabaseDao();
    private ExamDao examDao = new ExamDatabaseDao();
    private UserDao userDao = new UserDatabaseDao();

}
