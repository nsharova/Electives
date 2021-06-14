package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String errorMessage = "No such command!";
    request.setAttribute("errorMessage", errorMessage);
    return Paths.ERROR_PAGE;
  }

  @Override
  public String getName() {
    return "no-command";
  }
}
