package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import com.company.nsharova.exception.AppException;
import com.company.nsharova.model.entity.Course;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor

public class GetUserCommand implements Command {

    private final UserService userService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        List<User> courses = userService.findAll();
        request.setAttribute("users", courses);
        return Paths.USERS_PAGE;
    }

    @Override
    public String getName() {
        return "users";
    }
}
