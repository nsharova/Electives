package com.company.nsharova.dto;

import lombok.Data;

@Data
public class CourseDto {
  private String name;
  private String description;
  private Integer duration;
  private Integer ownerId;
  private Integer levelId;
}
