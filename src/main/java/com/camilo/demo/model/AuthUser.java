package com.camilo.demo.model;

public class AuthUser {

    private String username;
    private String password;

    public AuthUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

}
