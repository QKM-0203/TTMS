package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieUserService {
     int addUser(MovieUser user);
     List<MovieUser> getAdmins();
     List<MovieUser> getSells(Long cinemaId);
     int delById( Long id);
     MovieUser getAllByAccounts( String accounts);
}
