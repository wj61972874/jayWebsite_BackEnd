package com.jayblog.weisite.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginRequest {
    private String selectAccount;
    private String username; // 修改字段名为 username
    private String password;
    private Boolean captcha;
}
