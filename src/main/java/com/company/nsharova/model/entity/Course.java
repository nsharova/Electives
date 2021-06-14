package com.company.nsharova.model.entity;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

import lombok.Data;

@Data
public class Course implements Serializable {
  private int id;
  private String name;
  private String description;
  private Integer duration;
  private int ownerId;
  private int userLevel;
  private List<Theme> themes;
}
