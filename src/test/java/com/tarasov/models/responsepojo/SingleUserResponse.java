package com.tarasov.models.responsepojo;

import lombok.Data;

@Data
public class SingleUserResponse {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
