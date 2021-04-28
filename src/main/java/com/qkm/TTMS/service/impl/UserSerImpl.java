package com.qkm.TTMS.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qkm.TTMS.entity.User;
import com.qkm.TTMS.mapper.UserMapper;
import com.qkm.TTMS.service.UserSer;
import org.springframework.stereotype.Service;

@Service
public class UserSerImpl implements UserSer {
    private final UserMapper userMapper;

    public UserSerImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int addUser(User user) {
        try{
            return userMapper.insert(user);
        }catch(Exception e){
            return 0;
        }
    }
}
