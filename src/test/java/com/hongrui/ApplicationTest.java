package com.hongrui;

import com.hongrui.domain.security.service.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class ApplicationTest {

    @Test
    public void test_jwt(){
        JwtUtil util = new JwtUtil("jiumeng", SignatureAlgorithm.HS256);
        Map<String,Object> map = new HashMap<>();
        map.put("username","jiumeng");
        map.put("password","123");
        map.put("age",100);
        String jwtToken = util.encode("jiumeng", 30000, map);
        util.decode(jwtToken).forEach((key,value) -> System.out.println(key + ": " + value));
    }
}
