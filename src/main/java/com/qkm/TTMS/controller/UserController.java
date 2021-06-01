package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.entity.MovieUserRoles;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.service.MovieUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final MovieUserMapper movieUserMapper;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final MovieUserService userSer;

    public UserController(MovieUserService userSer, MovieUserRolesMapper movieUserRolesMapper, MovieUserMapper movieUserMapper) {
        this.userSer = userSer;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.movieUserMapper = movieUserMapper;
    }

    /**
     * 经理得到所有的管理员
     * @return  所有的管理员
     */
    @GetMapping("/getAdmins/{page}")
    public List<MovieUser> getAdmins(@PathVariable("page") int page){
      return userSer.getAdmins(page);
    }

    /**
     * 管理员得到所有的售票员和管理员,sellId为1则是售票员,否则是管理员
     * @param cinemaId  电影院Id
     * @return  所有的人员
     */
    @GetMapping("/getSells/{cinemaId}/{page}")
    public List<MovieUser> getSells(@PathVariable("cinemaId")int cinemaId,@PathVariable("page")int page){
        return  userSer.getSells(cinemaId,page);
    }


    /**
     * 管理员增加管理员或者售票员
     * @param movieUser   人员信息
     * @return  是否增加成功
     */
    @PostMapping("/addUser")
    public int addUser(@RequestBody MovieUser movieUser){
           int i = userSer.addUser(movieUser);
           if(i == -1){
               return -1;
           }
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
    @DeleteMapping("/delUser/{userId}")
    public int delUser(@PathVariable("userId")int userId){
            userSer.delById(userId);
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
    @GetMapping("/getSelf/{accounts}")
    public MovieUser getInformation(@PathVariable("accounts") String accounts){
       return userSer.getAllByAccounts(accounts);
    }


}
