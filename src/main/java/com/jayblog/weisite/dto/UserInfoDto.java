package com.jayblog.weisite.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {
    private int id;
    private String realName;
    private List<String> roles;
    private String username;
}
