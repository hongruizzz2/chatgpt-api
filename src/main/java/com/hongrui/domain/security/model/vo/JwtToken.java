package com.hongrui.domain.security.model.vo;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hongrui
 * @description Token 信息
 * @date 2024-10-18 20:09
 */
public class JwtToken implements AuthenticationToken {
    private final String jwt;

    public JwtToken(String jwt)
    {
        this.jwt = jwt;
    }

    /**
     * 等同于账户
     */
    @Override
    public Object getPrincipal()
    {
        return jwt;
    }

    /**
     * 等同于密码
     */
    @Override
    public Object getCredentials()
    {
        return jwt;
    }
}
