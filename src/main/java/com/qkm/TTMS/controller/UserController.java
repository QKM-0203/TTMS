package com.qkm.TTMS.controller;

import com.qkm.TTMS.config.SecurityConfig;
import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {


    private final MovieVideoService movieVideoService;
    private final MovieProducerService movieProducerService;
    private final MovieActorService movieActorService;
    private final MovieWriteService movieWriteService;
    private final MovieDirectorService movieDirectorService;
    private final SecurityConfig securityConfig;
    private final CommonService commonService;
    private final MovieUserMapper movieUserMapper;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final MovieUserService userSer;

    public UserController(MovieUserService userSer, MovieUserRolesMapper movieUserRolesMapper, MovieUserMapper movieUserMapper, CommonService commonService, SecurityConfig securityConfig, MovieActorService movieActorService, MovieDirectorService movieDirectorService, MovieWriteService movieWriteService, MovieVideoService movieVideoService, MovieProducerService movieProducerService) {
        this.userSer = userSer;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.movieUserMapper = movieUserMapper;
        this.commonService = commonService;
        this.securityConfig = securityConfig;
        this.movieActorService = movieActorService;
        this.movieDirectorService = movieDirectorService;
        this.movieWriteService = movieWriteService;
        this.movieVideoService = movieVideoService;
        this.movieProducerService = movieProducerService;
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
           movieUser.setPassword(securityConfig.encode().encode(movieUser.getPassword()));
           int i = userSer.addUser(movieUser);
           if(i == -1){
               return -1;
           }
            MovieUserRoles movieUserRoles = new MovieUserRoles();
            movieUserRoles.setUserId(movieUser.getId());
            if(movieUser.getSellId() == 1){
                movieUserRoles.setRoleId(4);
            }else{
                movieUserRoles.setRoleId(3);
            }
            movieUserRolesMapper.insert(movieUserRoles);
            return movieUser.getId();
    }


    /**
     * 删除管理员或者售票员
     */
    @DeleteMapping("/delUser/{userId}/{cinemaId}/{page}")
    public List<MovieUser> delUser(@PathVariable("userId")int userId,@PathVariable("cinemaId") int cinemaId,@PathVariable("page")int page){
        userSer.delById(userId);
        return getSells(cinemaId,page);
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


    /**
     * 修改个人的图片
     */
    @PostMapping("/setPicture/{id}")
    public String setPicture(@PathVariable("id")int id,MultipartHttpServletRequest request){
        MultipartFile files = request.getFile("self");
        List<String> strings = commonService.uploadPictures(files);
        if("上传成功".equals(strings.get(strings.size()-1))){
            movieUserMapper.updateIconById(id,strings.get(0));
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
    }

    /**
     * 修改演员的图片
     */
    @PostMapping("/setActorPicture/{Id}")
    public String setActorPicture(@PathVariable("Id")int Id,MultipartHttpServletRequest request){
        MultipartFile files = request.getFile("actor");
        List<String> strings = commonService.uploadPictures(files);
        if("上传成功".equals(strings.get(strings.size()-1))){
            movieActorService.updateActorPicture(strings.get(0),Id);
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
    }


    /**
     * 修改导演的图片
     */
    @PostMapping("/setDirectPicture/{id}")
    public String setDirectPicture(@PathVariable("id")int id,MultipartHttpServletRequest request){
        MultipartFile files = request.getFile("director");
        List<String> strings = commonService.uploadPictures(files);
        if("上传成功".equals(strings.get(strings.size()-1))){
            movieDirectorService.updateDirectorPicture(strings.get(0),id);
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
    }


    /**
     * 修改编剧的图片
     */
    @PostMapping("/setWritePicture/{id}")
    public String setWritePicture(@PathVariable("id")int id,MultipartHttpServletRequest request){
        MultipartFile files = request.getFile("writer");
        List<String> strings = commonService.uploadPictures(files);
        if("上传成功".equals(strings.get(strings.size()-1))){
            movieWriteService.updateWriterPicture(strings.get(0),id);
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
    }


    /**
     * 修改视频
     */
    @PostMapping("/setVideo/{id}")
    public String setVideoPicture(@PathVariable("id")int id,MultipartHttpServletRequest request){
        MultipartFile files = request.getFile("video");
        List<String> strings = commonService.uploadPictures(files);
        if("上传成功".equals(strings.get(strings.size()-1))){
            movieVideoService.updateVideo(strings.get(0),id);
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
    }



    /**
     * 修改制片人的图片
     */
    @PostMapping("/setProducerPicture/{id}")
    public String setProducerPicture(@PathVariable("id")int id,MultipartHttpServletRequest request){
        MultipartFile files = request.getFile("producer");
        List<String> strings = commonService.uploadPictures(files);
        if("上传成功".equals(strings.get(strings.size()-1))){
            movieProducerService.updateProducerPicture(strings.get(0),id);
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
    }


    /**
     * 修改演员的信息
     */
    @PostMapping("/setActor/{id}")
    public int setActor(@PathVariable("id")int id, @RequestBody MovieActor movieActor){
       return  movieActorService.updateActorById(movieActor);
    }



    /**
     * 修改导演的信息
     */
    @PostMapping("/setDirector/{id}")
    public int setDirector(@PathVariable("id")int id, @RequestBody MovieDirector movieDirector){
        return  movieDirectorService.updateDirectorById(movieDirector);
    }


    /**
     * 修改制片人的信息
     */
    @PostMapping("/setProducer/{id}")
    public int setProducer(@PathVariable("id")int id, @RequestBody MovieProducer movieProducer){
        return  movieProducerService.updateProducerById(movieProducer);
    }


    /**
     * 修改编剧的信息
     */
    @PostMapping("/setWriter/{id}")
    public int setWriter(@PathVariable("id")int id, @RequestBody MovieWriter movieWriter){
        return  movieWriteService.updateWriterById(movieWriter);
    }




}
