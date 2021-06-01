package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.service.CommonService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonServiceImpl implements CommonService {

    @Override
    public List<?> getPage(List<?> List, int page, int num) {
        if(List.size() <= page*num){
            return null;
        }else if(List.size() < page*num+num){
            return List.subList(page*num,List.size());
        }
        return List.subList(page*num,page*num+num);
    }
}
