package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserSer {
    public int addUser(User user);
}
