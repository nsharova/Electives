package com.company.nsharova.model.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int userRole;
    private boolean isLocked;
}
