package com.company.nsharova.controller;

import com.company.nsharova.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first");
        String lastName = request.getParameter("last");
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        User user2 = new User();
        user2.setFirstName("Oleg");
        user2.setLastName("Frolov");
        users.add(user);
        users.add(user2);
        request.setAttribute("users", users);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
