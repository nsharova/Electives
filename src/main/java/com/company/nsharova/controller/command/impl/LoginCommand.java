package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Level;
import com.company.nsharova.model.entity.Theme;
import com.company.nsharova.model.entity.User;
import com.company.nsharova.model.service.CourseService;
import com.company.nsharova.model.service.UserService;
import com.company.nsharova.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
public class LoginCommand implements Command {

    private static Logger logger = Logger.getLogger(LoginCommand.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String destination = Paths.LOGIN_PAGE;
        Optional<User> user = null;

        if ("GET".equals(request.getMethod())) {
            return destination;
        } else if ("POST".equals(request.getMethod())) {
            Map<String, String> errors = new HashMap<>();

            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if( login == null || login.equals("") || password == null || password.equals("")  ){
                return destination;
            }
            HttpSession session = request.getSession();

            if (errors.isEmpty()) {
                user = userService.getUserByLogin(login);
                if (user.isPresent() && user.get().getPassword().equals(password)){
                session.setAttribute("loggedUser", user.get());
                destination = "/controller?command=courses";
                }
            } else {

                session.setAttribute("errors", errors);
                session.setAttribute("tempUser", user.get());
            }

        }
        return destination;
    }

    @Override
    public String getName() {
        return "login";
    }
}
