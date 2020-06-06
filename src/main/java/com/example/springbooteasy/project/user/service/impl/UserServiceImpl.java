package com.example.springbooteasy.project.user.service.impl;


import com.example.springbooteasy.project.user.entity.User;
import com.example.springbooteasy.project.user.mapper.UserMapper;
import com.example.springbooteasy.project.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbooteasy.framework.web.domain.AjaxResult;


/**
* <p>
    *  服务实现类
    * </p>
*
* @author xiaorui
* @since 2020-06-06
*/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
    Integer id = userMapper.insert(user);
    return id;
    }

    @Override
    public int deleteById(Integer id) {
    Integer row = userMapper.deleteById(id);
    return row;
    }

    @Override
    public int update(User user) {
    int row = userMapper.update(user);
    return row;
    }


    @Override
    public User findUserById(Integer id) {
    User user = userMapper.findUserById(id);
    return user;
    }

}
