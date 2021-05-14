package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.RUser;
import com.qkm.TTMS.entity.Roles;
import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.mapper.MovieUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final MovieUserMapper movieUserMapper;

    public UserService(MovieUserMapper movieUserMapper) {
        this.movieUserMapper = movieUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MovieUser user = movieUserMapper.getByAccounts(s);

        if(user == null){
            throw new UsernameNotFoundException("accounts not find");
        }

        //返回实现userDetails的user类
        return new RUser(user);
    }
}
