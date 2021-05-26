package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.service.MovieUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieUserServiceImpl implements MovieUserService {
    private final MovieUserMapper userMapper;

    public MovieUserServiceImpl(MovieUserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int addUser(MovieUser user) {
        try{
            userMapper.insert(user);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public List<MovieUser> getAdmins() {
        return userMapper.getAdminByCinemaId();
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
