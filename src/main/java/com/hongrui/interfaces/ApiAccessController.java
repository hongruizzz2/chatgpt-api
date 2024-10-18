package com.hongrui.interfaces;

import com.hongrui.domain.security.service.JwtUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongrui
 * @description
 * @date 2024-10-18 19:49
 */
@RestController
public class ApiAccessController {
    private Logger logger = LoggerFactory.getLogger(ApiAccessController.class);
    private String token = "";
    @RequestMapping("/authorize")
    public ResponseEntity<Map<String,String>> authorize(String username, String password) {
        Map<String,String> map = new HashMap<>();
        // 模拟账号和密码校验
        if (!"jiumeng".equals(username) || !"123".equals(password)){
            map.put("msg","用户名密码错误");
            return ResponseEntity.ok(map);
        }
        //校验生成token
        JwtUtil jwtUtil = new JwtUtil();
        Map<String,Object> claim = new HashMap<>();
        claim.put("username",username);
        String jwtToken = jwtUtil.encode(username, 5 * 6 * 1000, claim);
        map.put("msg","授权成功");
        map.put("token",jwtToken);
        this.token = jwtToken;
        //返回token码
        return ResponseEntity.ok(map);
    }

    @RequestMapping("/verify")
    public ResponseEntity<String> verify(String token) {
        logger.info("验证 token: {}", token);
        if (ObjectUtils.equals(token,this.token)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping("/success")
    public String success(){
        return "test success By Jiumeng";
    }
}
