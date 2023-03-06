package com.tarasov.models.responsepojo;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserResponse {
    private String name;
    private String job;
    private Date updatedAt;
}
