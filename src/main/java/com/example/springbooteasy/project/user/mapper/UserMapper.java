package com.example.springbooteasy.project.user.mapper;

import com.example.springbooteasy.project.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author xiaorui
* @since 2020-06-06
*/
@Mapper
public interface UserMapper {

    int  insert(User user);
    int  deleteById(Integer id);
    int  update(User user);
    User findUserById(Integer id);
}
