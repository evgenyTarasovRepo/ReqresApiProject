package com.tarasov.models.requestpojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisterUser {

    @NonNull
    private String email;

    private String password;


}
