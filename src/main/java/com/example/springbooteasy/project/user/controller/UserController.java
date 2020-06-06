package com.example.springbooteasy.project.user.controller;

import com.example.springbooteasy.project.user.entity.User;
import com.example.springbooteasy.project.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.springbooteasy.framework.web.domain.AjaxResult;
    import org.springframework.web.bind.annotation.RestController;


/**
* <p>
*  内部接口
* </p>
*
* @author xiaorui
* @since 2020-06-06
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    /**
    * 新增
    *
    * @param user
    * @return
    */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody User user){
    int id = userService.insert(user);
    if(id > 0){
        return AjaxResult.success();
    }else{
        return AjaxResult.error();
    }
    }

    /**
    * 修改
    *
    * @param user
    * @return
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AjaxResult update(@RequestBody User user) {
    int row = userService.update(user);
    if (row > 0) {
        return AjaxResult.success();
    } else {
        return AjaxResult.error();
    }
    }

    /**
    * 删除
    *
    * @param id 主键id
    * @return
    */
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable int id) {
    int row = userService.deleteById(id);
    if (row > 0) {
        return AjaxResult.success();
    } else {
        return AjaxResult.error();
    }
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public AjaxResult findOne(@PathVariable int id) {
    User user = userService.findUserById(id);
    if (user == null) {
        return AjaxResult.error();
        }
    return AjaxResult.success(user);
    }

}
