package com.example.springbooteasy.project.system.controller;

import com.example.springbooteasy.common.constant.Constants;
import com.example.springbooteasy.common.utils.ServletUtils;
import com.example.springbooteasy.framework.security.LoginBody;
import com.example.springbooteasy.framework.security.LoginUser;
import com.example.springbooteasy.framework.security.service.SysLoginService;
import com.example.springbooteasy.framework.security.service.SysPermissionService;
import com.example.springbooteasy.framework.security.service.TokenService;
import com.example.springbooteasy.framework.web.domain.AjaxResult;
import com.example.springbooteasy.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 登录验证
 * 
 * @author xiaorui
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;


    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     * 
     * @param username 用户名
     * @param password 密码
     * @param captcha 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        System.out.println(loginBody.getUsername());
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        return ajax;
    }

}
