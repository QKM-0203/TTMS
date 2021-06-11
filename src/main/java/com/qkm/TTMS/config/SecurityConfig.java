package com.qkm.TTMS.config;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.io.PrintWriter;
import java.util.HashMap;


@Configuration(value = "securityConfig")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(MovieUserMapper movieUserMapper, UserService userService) {
        this.movieUserMapper = movieUserMapper;
        this.userService = userService;
    }

    @Bean//加密模式，记住这个验证时是加密验证的，那么存的时候也要加密后再存，
    public PasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    private final MovieUserMapper movieUserMapper;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/Login").permitAll()
//                .antMatchers("/getOrdersBySelf").hasRole("USER")
////                .antMatchers("/getSells").hasRole("ADMIN")
//                .antMatchers("/saveOrder").hasRole("USER")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/Login")
                .usernameParameter("accounts")
                //前后端分离,成功的Json
                .successHandler((req, resp, authentication) -> {
                    String accounts = req.getParameter("accounts");
                    MovieUser allByAccounts = movieUserMapper.getAllByAccounts(accounts);
                    Object principal = authentication.getPrincipal();
                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                    stringObjectHashMap.put("privilege",principal);
                    stringObjectHashMap.put("sign","1");
                    stringObjectHashMap.put("nickName",allByAccounts.getNickname());
                    stringObjectHashMap.put("icon",allByAccounts.getIcon());
                    stringObjectHashMap.put("cinemaId",allByAccounts.getCinemaId());
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(JSON.toJSONString(stringObjectHashMap));
                    out.flush();
                    out.close();
                })
               // 失败的Json
                .failureHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                    stringObjectHashMap.put("sign","0");
                    out.write(JSON.toJSONString( stringObjectHashMap));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()//注销功能
                   //注销跳转
                .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("注销成功!");
                            out.flush();
                            out.close();
                        }
                );

        //未认证的
// .csrf().disable().exceptionHandling()
//                //没有认证时，在这里处理结果，不要重定向
//                .authenticationEntryPoint((req, resp, authException) -> {
//                            resp.setContentType("application/json;charset=utf-8");
//                            resp.setStatus(401);
//                            PrintWriter out = resp.getWriter();
//                            RespBean respBean = RespBean.error("访问失败!");
//                            if (authException instanceof InsufficientAuthenticationException) {
//                                respBean.setMsg("请求失败，请联系管理员!");
//                            }
//                            out.write(new ObjectMapper().writeValueAsString(respBean));
//                            out.flush();
//                            out.close();
//                        }
//                );



    }

}
