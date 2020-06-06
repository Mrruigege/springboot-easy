package com.example.springbooteasy.project.user.service;

import com.example.springbooteasy.project.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbooteasy.framework.web.domain.AjaxResult;

/**
* <p>
*  服务类
* </p>
*
* @author xiaorui
* @since 2020-06-06
*/

public interface IUserService {

    int  insert(User user);
    int  deleteById(Integer id);
    int  update(User user);
    User  findUserById(Integer id);

}
