package com.qkm.TTMS.config;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 区域解析器,可以中英文切换,默认是根据请求头带的消息判断的.发送login请求就会自动使用解析器来获取参数看是是哪种语言
 */


public class MyLocaleResolve implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String i = httpServletRequest.getParameter("I");
        //获取系统默认的
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(i)){
            String[] s = i.split("_");
            locale = new Locale(s[0],s[1]);
        }
        return locale;
    }


    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }


}
