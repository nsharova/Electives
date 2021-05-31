package com.company.nsharova.extractor.impl;

import com.company.nsharova.dto.CourseDto;
import com.company.nsharova.extractor.Extractor;
import javax.servlet.http.HttpServletRequest;

public class CourseDtoExtractor implements Extractor<CourseDto, HttpServletRequest> {

  @Override
  public CourseDto extractFrom(HttpServletRequest request) {
    CourseDto dto = new CourseDto();

    dto.setName(request.getParameter("name"));
    dto.setDescription(request.getParameter("description"));
    try {
      dto.setDuration(Integer.valueOf(request.getParameter("duration")));
    } catch (NumberFormatException ex) {
    }
    try {
      dto.setLevelId(Integer.valueOf(request.getParameter("levelId")));
    } catch (NumberFormatException ex) {
    }
    dto.setOwnerId(Integer.valueOf(request.getParameter("ownerId")));

    return dto;
  }
}
