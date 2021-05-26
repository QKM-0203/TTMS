package com.qkm.TTMS.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 将数据库中的用户信息提取出来将用户名密码和和输入的比对,默认采用(bcrypt)加密比对,即数据库应存密文
 */
public class RUser implements UserDetails,Serializable {

    private final MovieUser user;

    public RUser(MovieUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Roles> roles = user.getRoles();
        if(!roles.isEmpty()){
            ArrayList<GrantedAuthority> list = new ArrayList<>();
            for (Roles role : roles) {
                list.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            return list;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccounts();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getCinemaId(Long id){
        return user.getCinemaId();

    }
}
