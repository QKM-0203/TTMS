package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.entity.MovieUserRoles;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.service.impl.UserSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCon {

    private final MovieUserMapper movieUserMapper;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final UserSerImpl userSer;

    public UserCon(UserSerImpl userSer, MovieUserRolesMapper movieUserRolesMapper, MovieUserMapper movieUserMapper) {
        this.userSer = userSer;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.movieUserMapper = movieUserMapper;
    }

    /**
     * 经理得到所有的管理员
     * @return
     */
    @GetMapping("/getAdmins")
    public List<MovieUser> getAdmins(){
       return  userSer.getAdmins();
    }

    /**
     * 管理员得到所有的售票员和管理员,sellId为1则是售票员,否则是管理员
     * @param cinemaId
     * @return
     */
    @GetMapping("/getSells")
    public List<MovieUser> getSells(@RequestParam("cinemaId")Long cinemaId){
        return  userSer.getSells(cinemaId);
    }


    /**
     * 管理员增加管理员或者售票员
     * @param movieUser
     * @return
     */
    @PostMapping("/addUser")
    public Long addUser(@RequestBody MovieUser movieUser){
            int i = userSer.addUser(movieUser);
            MovieUserRoles movieUserRoles = new MovieUserRoles();
            movieUserRoles.setUserId(movieUser.getId());
            if(movieUser.getSellId() == 1){
                movieUserRoles.setRoleId(4);
            }else{
                movieUserRoles.setRoleId(1);
            }
            movieUserRolesMapper.insert(movieUserRoles);
            return movieUser.getId();
    }


    /**
     * 删除管理员或者售票员
     */
    @DeleteMapping("/delUser")
    public int delUser(Long userid){
            movieUserRolesMapper.delByUserId(userid);
            userSer.delById(userid);
            return  1;
    }


    /**
     * 编辑个人信息
     */
    @PutMapping("/editSelf")
    public int edit(@RequestBody MovieUser movieUser){
      return  movieUserMapper.updateById(movieUser);
    }


    /**
     * 得到个人信息
     */
    @GetMapping("/getSelf")
    public MovieUser getInformation(@RequestParam("accounts") String accounts){
       return userSer.getAllByAccounts(accounts);
    }


}
