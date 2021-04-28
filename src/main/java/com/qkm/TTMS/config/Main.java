package com.qkm.TTMS.config;//package com.qkm.TTMS.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.matches("", "$2a$10$rbwDmyACHplFDyGETypfFuLMaQOUV.klJ5AfqN.IYtIJqrcoZwrBC"));

    }

}


