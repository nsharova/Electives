package com.company.nsharova.extractor.impl;

import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Theme;

import javax.servlet.http.HttpServletRequest;

public class ThemeExtractor implements Extractor<Theme, HttpServletRequest> {
    @Override
    public Theme extractFrom(HttpServletRequest request) {
        Theme theme = new Theme();
        theme.setName(request.getParameter("name"));
        return theme;
    }
}
