package com.company.nsharova.controller.command;

import com.company.nsharova.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandUtility {

    private static Logger logger = Logger.getLogger(CommandUtility.class);

    public static void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String url) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
    static void setUserRole(HttpServletRequest request,
                            User user, String name) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", name);
        //session.setAttribute("role", role);
    }

    public static String getDestinationForUser(int accessLevel) {
        String page = "";
        switch (accessLevel) {
            case 2:
                page = "/WEB-INF/view/entrant_menu.jsp";
                break;
            case 3:
                page = "/WEB-INF/view/admin_menu.jsp";
                break;
            default:
        }
        return page;
    }


/*
    static boolean checkUserIsLogged(HttpServletRequest request, String userName){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(userName::equals)){
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
 */
}
