package com.company.nsharova.model.service;
import com.company.nsharova.model.dao.ThemeDao;
import com.company.nsharova.model.entity.Theme;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ThemeService {
    private final ThemeDao themeDao;

    public List<Theme> findAll() {
        return themeDao.findAll();
    }

    public void create(Theme theme) {
        System.err.println(theme);
        themeDao.create(theme);
    }

    public boolean removeThemeById(Integer themeId) {
        return themeDao.remove(themeId);
    }

    //public List<Theme> findByIdCourse(Integer courseId) {return themeDao.};
}