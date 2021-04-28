package com.qkm.TTMS.Aop;

import com.qkm.TTMS.entity.MovieUserRoles;
import com.qkm.TTMS.entity.User;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

//   private final MovieUserRolesMapper movieUserRolesMapper;
//
//    public Aop(MovieUserRolesMapper movieUserRolesMapper) {
//        this.movieUserRolesMapper = movieUserRolesMapper;
//    }
//
//    @Pointcut("execution(* com.qkm.TTMS.controller.RegisterCon.addUser(..))"+"&& args(user)")
//    public void pointCut(User user){
//
//    }
//
//
//
//    /**
//     * 方法返回操作执行后在执行仅当没有异常的时候，如果出现异常，则就不执行返回操作，但是After还是执行的
//     * 在注册人员之后,同时给该人员加user职责,不然不能成功登录.
//     * @param user
//     */
//
//    @After("pointCut(user)")
//    public void after(User user){
//
//        MovieUserRoles movieUserRoles = new MovieUserRoles();
//        movieUserRoles.setUserId(user.getId());
//        movieUserRoles.setRoleId(2);
//        movieUserRolesMapper.insert(movieUserRoles);
//    }




}

