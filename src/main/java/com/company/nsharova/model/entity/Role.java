package com.company.nsharova.model.entity;

public enum Role {
    ADMIN(1), TEACHER(2), STUDENT(3), GUEST(4);

    private final int userRole;

    Role(int userRole) {
        this.userRole = userRole;
    }

    public int getAccessLevel() {
        return userRole;
    }
}
