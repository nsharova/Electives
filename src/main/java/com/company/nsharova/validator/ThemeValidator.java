package com.company.nsharova.validator;

import com.company.nsharova.model.entity.Theme;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ThemeValidator implements Validator<Theme> {
    @Override
    public void validate(Theme dto, Map<String, String> errors) {
        if (StringUtils.isBlank(dto.getName())) {
            errors.put("name", "Name cannot be blank");
        }
    }
}
