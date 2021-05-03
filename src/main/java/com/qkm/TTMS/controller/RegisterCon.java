package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.config.SecurityConfig;
import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.mapper.PeopleWantMapper;
import com.qkm.TTMS.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController //返回的是JSON对像,不是JSON字符串
public class RegisterCon {

    private final UserSer userSer;
    private final SecurityConfig securityConfig;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final PeopleWantMapper peopleWantMapper;


    public RegisterCon(UserSer userSer, SecurityConfig securityConfig, MovieUserRolesMapper movieUserRolesMapper, PeopleWantMapper peopleWantMapper) {
        this.userSer = userSer;
        this.securityConfig = securityConfig;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.peopleWantMapper = peopleWantMapper;
    }



    @PostMapping("/register")
    public String addUser(MovieUser user){
        user.setCreateTime(new Date());
        user.setPassword(securityConfig.encode().encode(user.getPassword()));
        //普通用户
        user.setCinemaId(-1L);
        try{
            MovieUserRoles movieUserRoles = new MovieUserRoles();
            movieUserRoles.setUserId(user.getId());
            movieUserRoles.setRoleId(2);
            movieUserRolesMapper.insert(movieUserRoles);
            return JSON.toJSONString("注册成功");
        }catch (Exception e){
            return JSON.toJSONString("注册失败,帐号已经注册");
        }

    }




    @GetMapping("/wl")
    public  List<Long> Wl(){
        List<Long> longs = peopleWantMapper.selectMovieIdByAccounts("123@qq.com");
        return longs;
    }



}
