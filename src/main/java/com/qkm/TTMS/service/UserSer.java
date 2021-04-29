package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieUser;
import org.springframework.stereotype.Service;

@Service
public interface UserSer {
    public int addUser(MovieUser user);
}
