package com.example.springbooteasy.framework.security.service;

import com.example.springbooteasy.common.constant.Constants;
import com.example.springbooteasy.common.exception.CustomException;
import com.example.springbooteasy.common.exception.user.CaptchaException;
import com.example.springbooteasy.common.exception.user.CaptchaExpireException;
import com.example.springbooteasy.common.exception.user.UserPasswordNotMatchException;
import com.example.springbooteasy.framework.redis.RedisCache;
import com.example.springbooteasy.framework.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 * 
 * @author xiaorui
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 拿到redis中的验证码，并删除
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            // 验证码错
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    // 这里创建Authentication子类UsernamePasswordAuthenticationToken去验证密码的合法性
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                // 密码错误
                throw new UserPasswordNotMatchException();
            }
            else
            {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token，并返回
        return tokenService.createToken(loginUser);
    }
}
