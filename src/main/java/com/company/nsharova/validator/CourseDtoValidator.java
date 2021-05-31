package com.company.nsharova.validator;

import com.company.nsharova.dto.CourseDto;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class CourseDtoValidator implements Validator<CourseDto> {

  @Override
  public void validate(CourseDto dto, Map<String, String> errors) {
    if (StringUtils.isBlank(dto.getName())) {
      errors.put("name", "Name cannot be blank");
    }
    if (StringUtils.isBlank(dto.getDescription())) {
      errors.put("description", "Description cannot be blank");
    }
  }
}
