package com.company.nsharova.extractor.impl;

import com.company.nsharova.dto.CourseDto;
import com.company.nsharova.extractor.Extractor;
import com.company.nsharova.model.entity.Course;

public class CourseExtractor implements Extractor<Course, CourseDto> {

  @Override
  public Course extractFrom(CourseDto source) {
    Course entity = new Course();

    entity.setName(source.getName());
    entity.setDescription(source.getDescription());
    entity.setDuration(source.getDuration());
    entity.setOwnerId(source.getOwnerId());
    entity.setUserLevel(source.getLevelId());

    return entity;
  }
}
