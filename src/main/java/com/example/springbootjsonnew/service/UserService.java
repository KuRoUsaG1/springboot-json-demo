package com.example.springbootjsonnew.service;

import com.example.springbootjsonnew.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserInfo> getUserInfo();
}
