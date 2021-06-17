package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.service.UserService;
import com.company.nsharova.validator.Validator;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public abstract class AddUserCommand implements Command {
    private final Extractor<User, HttpServletRequest> userExtractor;
    private final Validator<User> userValidator;
    private final UserService userService;
    private final String startDestination;
    private final String forwardDestination;
    private final int userRole;
    private final String userCommandName;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String destination = startDestination;

        if ("POST".equals(request.getMethod())) {
            Map<String, String> errors = new HashMap<>();

            User user = userExtractor.extractFrom(request);
            userValidator.validate(user, errors);

            if (errors.isEmpty()) {
                userService.create(user);
                user.setUserRole(userRole);
                destination = forwardDestination;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("errors", errors);
                session.setAttribute("tempCourse", user);
            }
        }
        return destination;
    }

    @Override
    public String getName() {
        return userCommandName;
    }
}
