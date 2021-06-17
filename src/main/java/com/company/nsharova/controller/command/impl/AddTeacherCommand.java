package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.service.UserService;
import com.company.nsharova.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class AddTeacherCommand extends AddUserCommand {

    public AddTeacherCommand(
            Extractor<User, HttpServletRequest> userExtractor,
            Validator<User> userValidator,
            UserService userService) {
        super(userExtractor, userValidator, userService, Paths.USERS_PAGE,
                "/controller?command=users", 2, "add_teacher");
    }
}
