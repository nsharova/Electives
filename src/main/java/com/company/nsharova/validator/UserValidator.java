package com.company.nsharova.validator;

import com.company.nsharova.model.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class UserValidator implements Validator<User>{

    @Override
    public void validate(User dto, Map<String, String> errors) {
        if (StringUtils.isBlank(dto.getLogin())) {
            errors.put("login", "Login cannot be blank");
        }
        if (StringUtils.isBlank(dto.getPassword())) {
            errors.put("password", "Password cannot be blank");
        }
    }

    public static boolean  isLoginValid(String login) {
        final String regex = "[A-Zа-яА-Яa-z0-9-]+";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(login);
        return m.matches();

    }

    public static boolean  isPasswordValid(String password) {
        final String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,15}";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }
}
