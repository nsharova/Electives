package com.company.nsharova.model.entity;

import lombok.Data;
import java.util.List;

@Data
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int userRole;
}
