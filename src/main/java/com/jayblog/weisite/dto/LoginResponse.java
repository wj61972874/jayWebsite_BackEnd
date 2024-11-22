package com.jayblog.weisite.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private int id;
    private String password;
    private String realName;
    private List<String> roles;
    private String username;
    private String accessToken;
}
