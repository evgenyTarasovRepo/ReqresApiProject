package com.tarasov.models.responsepojo;

import lombok.Data;

@Data
public class RegisterUserSuccessResponse {
    private Integer id;
    private String token;
}
