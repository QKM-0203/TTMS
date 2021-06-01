package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieUserService {
     int addUser(MovieUser user);
     List<MovieUser> getAdmins(int page);
     List<MovieUser> getSells(int cinemaId,int page);
     int delById( int id);
     MovieUser getAllByAccounts( String accounts);
}
