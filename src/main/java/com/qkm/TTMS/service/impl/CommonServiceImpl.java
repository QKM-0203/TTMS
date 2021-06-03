package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.CommonService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getSizeAndInfo(List<?> list, int offset,int page) {
        int size = list.size();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("list",(List<Movie>)getPage(list,page,offset));
        stringObjectHashMap.put("size",size/offset);
        return stringObjectHashMap;
    }
}
