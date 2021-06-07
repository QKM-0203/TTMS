package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.entity.UserOrder;
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

//    @Override
//    public List<MovieUser> getAdmins(int page) {
//        Page<MovieUser> movieUserPage = new Page<>(page,5,true);
//        return (List<MovieUser>)
//                userMapper.select(movieUserPage,null);
//    }

    @Override
    public List<MovieUser> getSells(int cinemaId,int page) {
        Page<MovieUser> movieOrderPage = new Page<>(page,5,true);
        IPage<MovieUser> movieUserIPage = userMapper.selectByCinemaId(movieOrderPage, cinemaId);
        List<MovieUser> movieUserIPageList = movieUserIPage.getRecords();
        if(movieUserIPageList.size() == 0){
            return null;
        }
        movieUserIPageList.add(new MovieUser(String.valueOf(movieUserIPage.getPages())));
        return movieUserIPageList;
    }

    @Override
    public int delById(int id) {
        return userMapper.delById(id);
    }

    @Override
    public MovieUser getAllByAccounts(String accounts) {
        return userMapper.getAllByAccounts(accounts);
    }
}
