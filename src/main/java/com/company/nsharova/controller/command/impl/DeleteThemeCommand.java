package com.company.nsharova.controller.command.impl;

import com.company.nsharova.controller.command.Command;
import com.company.nsharova.model.service.CourseService;
import com.company.nsharova.model.service.ThemeService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class DeleteThemeCommand implements Command {
    private final ThemeService themeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("START");
        Integer themeId = Integer.valueOf(request.getParameter("themeId"));

        System.err.println(themeId);
        if (!themeService.removeThemeById(themeId)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return "/controller?command=themes";
    }

    @Override
    public String getName() {
        return "delete_theme";
    }
}
