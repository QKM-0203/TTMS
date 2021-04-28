package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.RUser;
import com.qkm.TTMS.entity.Roles;
import com.qkm.TTMS.entity.User;
import com.qkm.TTMS.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.getByaccounts(s);
        if(user == null){
            throw new UsernameNotFoundException("accounts not find");
        }
        // 将权限全部放入
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Roles r: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        }

        //返回实现userDetails的user类
        return new RUser(user);
    }
}
