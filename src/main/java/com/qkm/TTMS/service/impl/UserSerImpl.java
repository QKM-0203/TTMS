package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.service.UserSer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSerImpl implements UserSer {
    private final MovieUserMapper userMapper;

    public UserSerImpl(MovieUserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int addUser(MovieUser user) {

            return userMapper.insert(user);
    }

    @Override
    public List<MovieUser> getAdmins(Long cinemaId) {
        return userMapper.getAdminByCinemaId(cinemaId);
    }

    @Override
    public List<MovieUser> getSells(Long cinemaId) {
        return userMapper.getSellByCinemaId(cinemaId);
    }

    @Override
    public int delById(Long id) {
        return userMapper.delById(id);
    }

    @Override
    public MovieUser getAllByAccounts(String accounts) {
        return userMapper.getAllByAccounts(accounts);
    }
}
