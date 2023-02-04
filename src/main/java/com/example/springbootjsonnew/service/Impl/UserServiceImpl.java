package com.example.springbootjsonnew.service.Impl;

import com.example.springbootjsonnew.dto.UserInfo;
import com.example.springbootjsonnew.entity.User;
import com.example.springbootjsonnew.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${end-point}")
    private String endPoint;

    @Override
    public List<UserInfo> getUserInfo() {
        List<User> users = retrieveUsers();
        if (users == null || users.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserInfo> userInfo = new ArrayList<>();
        for (User user : users) {
            UserInfo info = new UserInfo();
            info.setUsername(user.getUserName());
            info.setEmail(user.getEmail());
            info.setZipcode(user.getAddress().getZipcode());
            userInfo.add(info);
        }
        return userInfo;
    }
    private List<User> retrieveUsers() {
        List<User> users = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(endPoint);
            users= mapper.readValue(url, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.out.println(e);
        }
        return users;
    }
}
