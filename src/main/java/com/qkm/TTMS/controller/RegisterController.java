package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.config.SecurityConfig;
import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.mapper.PeopleWantMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController //自动将JSON对新象转为字符串
@CrossOrigin
public class RegisterController {

    private final SecurityConfig securityConfig;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final PeopleWantMapper peopleWantMapper;

    private final MovieUserMapper movieUserMapper;

    public RegisterController(SecurityConfig securityConfig, MovieUserRolesMapper movieUserRolesMapper, PeopleWantMapper peopleWantMapper, MovieUserMapper movieUserMapper) {
        this.securityConfig = securityConfig;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.peopleWantMapper = peopleWantMapper;
        this.movieUserMapper = movieUserMapper;
    }



    @PostMapping("/register")
    public String addUser(@RequestBody MovieUser user){
        user.setCreateTime(new Date());
        user.setPassword(securityConfig.encode().encode(user.getPassword()));
        //普通用户
        user.setCinemaId(-1L);
        try{
            movieUserMapper.insert(user);
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
