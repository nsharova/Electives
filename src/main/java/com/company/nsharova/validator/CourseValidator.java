package com.company.nsharova.validator;

import com.company.nsharova.model.entity.Course;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class CourseValidator implements Validator<Course> {

  @Override
  public void validate(Course dto, Map<String, String> errors) {
    if (StringUtils.isBlank(dto.getName())) {
      errors.put("name", "Name cannot be blank");
    }
    if (StringUtils.isBlank(dto.getDescription())) {
      errors.put("description", "Description cannot be blank");
    }
  }
}
