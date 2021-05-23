package com.company.nsharova.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseAssigment implements Serializable {
    private int userId;
    private int courseId;
    private int courseStatus;
    private int mark;
}
