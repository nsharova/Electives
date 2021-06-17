package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.service.UserService;
import com.company.nsharova.validator.Validator;
import javax.servlet.http.HttpServletRequest;



public class AddStudentCommand extends AddUserCommand{

    public AddStudentCommand(
            Extractor<User, HttpServletRequest> userExtractor,
            Validator<User>userValidator,
            UserService userService
            ) {
        super(userExtractor,userValidator,userService, Paths.REGISTRATION_PAGE,
                "/controller?command=courses",3,"add_student");
    }
}
