package com.company.nsharova.controller;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import com.company.nsharova.controller.command.CommandContainer;
import com.company.nsharova.exception.AppException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class DispatcherController extends HttpServlet {



  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  private void process(HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {
    String commandName = request.getParameter("command");

    Command command = CommandContainer.get(commandName);
    System.out.println("Command name "+commandName);

    String forward = Paths.ERROR_PAGE;
    try {
      System.out.println("DElETE");
      forward = command.execute(request, response);
      System.out.println("DElETE2");
    } catch (AppException ex) {
      request.setAttribute("errorMessage", ex.getMessage());
    }
    request.getRequestDispatcher(forward).forward(request, response);
  }
}
