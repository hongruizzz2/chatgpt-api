package com.hongrui.domain.security.service.realm;

import com.hongrui.domain.security.model.vo.JwtToken;
import com.hongrui.domain.security.service.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hongrui
 * @description 自定义 Realm
 * @date 2024-10-18 19:58
 */
public class JwtRealm extends AuthorizingRealm {
    private final Logger logger = LoggerFactory.getLogger(JwtRealm.class);

    private static final JwtUtil jwtUtil = new JwtUtil();

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        // 暂时不需要实现
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        String jwt = (String) token.getPrincipal();

        if (jwt == null)
        {
            throw new NullPointerException("jwtToken 不允许为空");
        }

        // 判断
        if (!jwtUtil.isVerify(jwt))
        {
            throw new UnknownAccountException();
        }

        // 可以获取 username 信息，并做一些处理
        String username = (String) jwtUtil.decode(jwt).get("username");
        logger.info("鉴权用户 username：{}", username);

        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
    }
}
