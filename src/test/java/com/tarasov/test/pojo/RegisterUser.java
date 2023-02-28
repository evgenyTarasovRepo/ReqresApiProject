package com.tarasov.test.pojo;

public class RegisterUser {
    private String email;
    private String password;

    public RegisterUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RegisterUser(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
