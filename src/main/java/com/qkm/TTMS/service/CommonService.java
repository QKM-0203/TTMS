package com.qkm.TTMS.service;



import java.util.List;
import java.util.Map;


public interface CommonService  {
    List<?> getPage(List<?> List, int page, int num);
    Map<String,Object> getSizeAndInfo(List<?> list,int offset,int page);
}
