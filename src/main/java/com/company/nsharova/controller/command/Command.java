package com.company.nsharova.controller.command;

import com.company.nsharova.exception.AppException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
  String execute(HttpServletRequest request, HttpServletResponse response) throws AppException;
  String getName();
}
