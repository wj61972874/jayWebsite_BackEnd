package com.jayblog.weisite.controller;

import com.jayblog.weisite.common.ResponseBean;
import com.jayblog.weisite.domain.User;
import com.jayblog.weisite.dto.LoginRequest;
import com.jayblog.weisite.dto.LoginResponse;
import com.jayblog.weisite.dto.UserInfoDto;
import com.jayblog.weisite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/manage/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseBean<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("loginRequest=======: " + loginRequest);
        try {
            LoginResponse loginResponse = userService.login(loginRequest);
            return ResponseBean.success(loginResponse);
        } catch (RuntimeException e) {
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseBean<UserInfoDto> getUserInfo(@RequestHeader("Authorization") String authorization) {
        System.out.println("authorization=======: " + authorization);
        String token = authorization.substring(7); // Remove "Bearer " prefix
        UserInfoDto user = userService.getUserFromToken(token);
        return ResponseBean.success(user);
    }
}