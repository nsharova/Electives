package com.company.nsharova.extractor.impl;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class   UserExtractor implements Extractor<User, HttpServletRequest> {
    @Override
    public User extractFrom(HttpServletRequest request) {
        User user = new User();

        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            user.setUserRole(Integer.parseInt(request.getParameter("role")));
        } catch (NumberFormatException ex) {
        }
        return user;
    }
}
