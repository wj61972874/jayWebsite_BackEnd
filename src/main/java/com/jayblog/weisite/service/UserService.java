package com.jayblog.weisite.service;

import com.jayblog.weisite.domain.User;
import com.jayblog.weisite.dto.LoginRequest;
import com.jayblog.weisite.dto.LoginResponse;
import com.jayblog.weisite.dto.UserInfoDto;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    UserInfoDto getUserFromToken(String token);

}