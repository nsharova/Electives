package com.company.nsharova.model.entity;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

@Data
public class Course implements Serializable {
    private int id;
    private String name;
    private String description;
    private Duration duration;
    private int userLevel;
    private List<Theme> themes;
}
