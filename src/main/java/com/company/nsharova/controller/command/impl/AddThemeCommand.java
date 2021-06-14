package com.company.nsharova.controller.command.impl;

import com.company.nsharova.constant.Paths;
import com.company.nsharova.controller.command.Command;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Theme;
import com.company.nsharova.model.service.ThemeService;
import com.company.nsharova.validator.Validator;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class  AddThemeCommand implements Command {
    private final Extractor<Theme, HttpServletRequest> themeExtractor;
    private final Validator<Theme> themeValidator;
    private final ThemeService themeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String destination = Paths.ADD_THEME_PAGE;
        if ("POST".equals(request.getMethod())) {
            Map<String, String> errors = new HashMap<>();

            Theme theme = themeExtractor.extractFrom(request);
            themeValidator.validate(theme, errors);

            if (errors.isEmpty()) {
                themeService.create(theme);
                destination = "/controller?command=themes";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("errors", errors);
                session.setAttribute("tempTheme", theme);
            }
        }
        return destination;
    }

    @Override
    public String getName() {
        return "add_theme";
    }
}
