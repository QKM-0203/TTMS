package com.qkm.TTMS.service;



import com.qkm.TTMS.entity.UserOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface CommonService  {
    List<?> getPage(List<?> List, int page, int num);
    Map<String,Object> getSizeAndInfo(List<?> list,int offset,int page);
    int justPage(List<?> list,int offset);
    List<String>  uploadPictures(MultipartFile files);
    int addAllMoney(UserOrder userOrder);
}
