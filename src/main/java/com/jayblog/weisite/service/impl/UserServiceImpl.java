package com.jayblog.weisite.service.impl;

import com.jayblog.weisite.domain.User;
import com.jayblog.weisite.domain.UserRole;
import com.jayblog.weisite.dto.LoginRequest;
import com.jayblog.weisite.dto.LoginResponse;
import com.jayblog.weisite.dto.UserInfoDto;
import com.jayblog.weisite.repository.UserRepository;
import com.jayblog.weisite.service.UserService;
import com.jayblog.weisite.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserInfoDto getUserFromToken(String token) {
        System.out.println("token=======: " +jwtSecret+"===="+ token);
        Claims claims = jwtUtil.extractAllClaims(token);
        String username = claims.getSubject();
        User user = userRepository.findByUsername(username);
        UserInfoDto userInfo = new UserInfoDto();
        System.out.println("user=======: " + user);
        if (user != null) {
            userInfo.setId(user.getId());
            userInfo.setRealName(user.getRealName());
            userInfo.setUsername(user.getUsername());
            userInfo.setRoles(user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()));
        }
        return userInfo;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            String token = generateAccessToken(user);
            LoginResponse response = new LoginResponse();
            response.setId(user.getId());
            response.setPassword(user.getPassword());
            response.setRealName(user.getRealName());
            response.setRoles(user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()));
            response.setUsername(user.getUsername());
            response.setAccessToken(token);
            return response;
        } else {
            throw new RuntimeException("用户名或密码错误");
        }
    }

    private String generateAccessToken(User user) {
        return jwtUtil.generateToken(user);
    }
}