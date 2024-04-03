package com.example.service;

import com.example.domain.ContactUser;
import com.example.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int addUser(User user);

    User queryUser(Map<String, Object> map);

    int updateUser(User user);

    int deleteUser(Map<String, Object> map);

    List<User> queryAllUser(Map<String, Object> map);

    int countuser(Map<String, Object> map);

    List<ContactUser> querycontactuser(Long uid);

    List<User> queryuserby(Map<String, Object> map);
}
