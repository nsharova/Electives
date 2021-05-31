package com.company.nsharova.model.entity;

public enum Status {
    BEGINNER(1),
    INTERMEDIATE(2),
    EXPERT(3),
    ALL_LEVELS(4);

    private final int courseStatus;

    Status(int courseStatus) {
        this.courseStatus = courseStatus;
    }
    public int getAccessLevel() {
        return courseStatus;
    }
}
