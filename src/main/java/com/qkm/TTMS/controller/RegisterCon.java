package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.config.SecurityConfig;
import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.service.UserSer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController //返回的是JSON对像,不是JSON字符串
public class RegisterCon {

    private final UserSer userSer;

    private final SecurityConfig securityConfig;

    public RegisterCon(UserSer userSer, SecurityConfig securityConfig, MovieUserRolesMapper movieUserRolesMapper) {
        this.userSer = userSer;
        this.securityConfig = securityConfig;
        this.movieUserRolesMapper = movieUserRolesMapper;
    }

    private final MovieUserRolesMapper movieUserRolesMapper;


    @PostMapping("/register")
    public String addUser(MovieUser user){
        user.setCreateTime(new Date());
        user.setPassword(securityConfig.encode().encode(user.getPassword()));
        int sign = userSer.addUser(user);
        Map<String, Object> map = new HashMap<>();
        if(sign == 1){
            MovieUserRoles movieUserRoles = new MovieUserRoles();
            System.out.println();
            movieUserRoles.setUserId(user.getId());
            movieUserRoles.setRoleId(2);
            movieUserRolesMapper.insert(movieUserRoles);
            map.put("sign","注册成功");
        }else{
            map.put("sign","注册失败,帐号已经注册");
        }
        return JSON.toJSONString(map);
    }


    @GetMapping("/seat")
    public String buy(){
       return "1";
    }



}
