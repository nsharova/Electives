package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return Paths.REGISTRATION_PAGE;
    }

    @Override
    public String getName() {
        return "registration";
    }
}
