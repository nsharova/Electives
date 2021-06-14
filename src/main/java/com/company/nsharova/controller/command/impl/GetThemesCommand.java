package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import com.company.nsharova.model.entity.Theme;
import com.company.nsharova.model.service.ThemeService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
public class GetThemesCommand implements Command {

    private final ThemeService themeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Theme> themes = themeService.findAll();
        request.setAttribute("themes", themes);
        return Paths.THEMES_PAGE;
    }

    @Override
    public String getName() {
        return "themes";
    }
}
