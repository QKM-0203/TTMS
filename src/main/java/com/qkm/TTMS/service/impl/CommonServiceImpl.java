package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.CinemaMoviesMapper;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.CommonService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Repository
public class CommonServiceImpl implements CommonService {
    private final CinemaMoviesService cinemaMoviesService;
    private final AreaCinemaService areaCinemaSer;
    private final MovieService movieSer;


    public CommonServiceImpl( CinemaMoviesService cinemaMoviesService, AreaCinemaService areaCinemaSer, MovieService movieSer) {
        this.cinemaMoviesService = cinemaMoviesService;
        this.areaCinemaSer = areaCinemaSer;
        this.movieSer = movieSer;
    }

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

    @Override
    public int justPage(List<?> list, int offset) {
        if(list.size() <= offset){
            return 0;
        }else{
            return list.size()/offset;
        }
    }

    @Override
    public int addAllMoney(UserOrder userOrder) {
        //存票房
        movieSer.addMoney(userOrder.getOrderMoney(), userOrder.getMovieId());

        //存电影院赚的钱
        areaCinemaSer.addMoney(userOrder.getOrderMoney(), userOrder.getCinemaId());

        //存储电影院的某部电影赚得钱
        int idByCinemaIdAndMovieId = cinemaMoviesService.getIdByCinemaIdAndMovieId(userOrder.getCinemaId(), userOrder.getMovieId());
        cinemaMoviesService.addMoney(userOrder.getOrderMoney(),idByCinemaIdAndMovieId);
        return 1;
    }


    @Override
    public List<String> uploadPictures(MultipartFile file) {
        ArrayList<String> strings = new ArrayList<>();
        //缓存输出流
        BufferedOutputStream stream = null;
        String filePath = "/home/qkm/Web/static/image/";
        if (!file.isEmpty()){
            String originalFilename = file.getOriginalFilename()+String.valueOf(new Date());
            try {
                byte[] bytes = file.getBytes();
                stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+originalFilename)));
                stream.write(bytes);
                strings.add(filePath+originalFilename);
                stream.close();
            } catch (IOException e) {
                stream = null;
                strings.add("文件上传失败");
                return  strings;
            }
        }else {
            strings.add("上传失败因为存在文件为空");
            return strings;
        }

        strings.add("上传成功");
        return strings;
    }
}
