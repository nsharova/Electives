package com.company.nsharova.model.entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class Theme implements Serializable {
    private int id;
    private String name;
}
