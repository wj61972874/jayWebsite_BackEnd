package com.jayblog.weisite.service.impl;

import com.jayblog.weisite.domain.User;
import com.jayblog.weisite.repository.UserRepository;
import com.jayblog.weisite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
