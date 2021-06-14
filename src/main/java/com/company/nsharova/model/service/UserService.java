package com.company.nsharova.model.service;

import com.company.nsharova.model.dao.UserDao;
import com.company.nsharova.model.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void create(User user) {
        System.err.println(user);
        userDao.create(user);
    }

    public boolean removeCourseById(Integer courseId) {
        return userDao.remove(courseId);
    }

    public User get
}
