package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.domain.ContactUser;
import com.example.domain.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User queryUser(Map<String, Object> map) {
        return userMapper.queryUser(map);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(Map<String, Object> map) {
        return userMapper.deleteUser(map);
    }

    @Override
    public List<User> queryAllUser(Map<String, Object> map) {
        return userMapper.queryAllUser(map);
    }

    @Override
    public int countuser(Map<String, Object> map) {
        return userMapper.countuser(map);
    }

    @Override
    public List<ContactUser> querycontactuser(Long uid) {
        return userMapper.querycontactuser(uid);
    }

    @Override
    public List<User> queryuserby(Map<String, Object> map) {
        return userMapper.queryuserby(map);
    }

    @Override
    public User queryUserById(Long uid) {
        return userMapper.queryUserById(uid);
    }
}
