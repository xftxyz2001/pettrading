package com.example.dao;

import com.example.domain.ContactUser;
import com.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    //增加用户
    int addUser(User user);

    //查找单个用户
    User queryUser(Map<String, Object> map);

    //修改用户信息
    int updateUser(User user);

    //删除用户
    int deleteUser(Map<String, Object> map);

    //查找所有普通用户
    List<User> queryAllUser(Map<String, Object> map);

    //添加用户数量
    int countuser(Map<String, Object> map);

    //查找用户最近联系人
    List<ContactUser> querycontactuser(Long uid);

    //更加条件查询用户
    List<User> queryuserby(Map<String, Object> map);
}
