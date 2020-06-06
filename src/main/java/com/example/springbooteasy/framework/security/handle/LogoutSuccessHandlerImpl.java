package com.example.springbooteasy.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.example.springbooteasy.common.constant.Constants;
import com.example.springbooteasy.common.constant.HttpStatus;
import com.example.springbooteasy.common.utils.ServletUtils;
import com.example.springbooteasy.common.utils.StringUtils;
import com.example.springbooteasy.framework.manager.AsyncManager;
import com.example.springbooteasy.framework.security.LoginUser;
import com.example.springbooteasy.framework.security.service.TokenService;
import com.example.springbooteasy.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author xiaorui
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
