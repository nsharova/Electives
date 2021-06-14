package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    private static Logger logger = Logger.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("logout");
        request.getSession().invalidate();
        return Paths.MAIN_PAGE;
    }

    @Override
    public String getName() {
        return "logout";
    }
}
