package com.company.nsharova.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseAssigment implements Serializable {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userLogin;
    private int userRole;
    private int courseId;
    private String courseName;
    private String courseDuration;
    private String courseDescription;
    private String courseOwner;
    private int courseStatus;
    private int mark;
}
