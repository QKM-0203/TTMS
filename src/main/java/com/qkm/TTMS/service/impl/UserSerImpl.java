package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.service.UserSer;
import org.springframework.stereotype.Service;

@Service
public class UserSerImpl implements UserSer {
    private final MovieUserMapper userMapper;

    public UserSerImpl(MovieUserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int addUser(MovieUser user) {
        try{
            return userMapper.insert(user);
        }catch(Exception e){
            return 0;
        }
    }
}
