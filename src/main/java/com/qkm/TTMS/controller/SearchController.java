package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.CommonService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SearchController {

    private final int TYPE = 1;
    private final int AREA = 2;
    private final int TIME = 3;

    private final CommonService commonService;
    private final CinemaMoviesService cinemaMoviesSer;
    private final AreaCinemaService areaCinemaSer;
    private final MovieService movieSer;

    public SearchController(MovieService movieSer, AreaCinemaService areaCinemaSer, CinemaMoviesService cinemaMoviesSer, CommonService commonService) {
        this.movieSer = movieSer;
        this.areaCinemaSer = areaCinemaSer;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.commonService = commonService;
    }

    /**
     * 按照电影名字查询,查询出包含该关键字的
     * @param movieName  电影名字
     * @return 所有的电影信息
     */
    @GetMapping("/searchMoviesByName/{movieName}/{page}")
    public List<Movie> getMovies(@PathVariable("movieName")String movieName,@PathVariable("page")int page){
        System.out.println(1);
        System.out.println(movieName);
        List<Movie> movies = movieSer.getMovies();
        List<Movie> pattern = pattern(movieName, movies, 4);
        if(pattern != null){
            List<Movie> movieList = (List<Movie>)commonService.getPage(pattern, page, 10);
            movieList.add(new Movie(commonService.justPage(pattern,10)));
            return movieList;
        }else{
            return null;
        }


    }

    /**
     * 根据影院名字查影院
     * @param cinemaName   电影院的名字
     * @return  所有的电影院信息
     */
    @GetMapping("/searchCinemasByName/{cinemaName}/{page}")
    public List<AreaCinemas> getCinemasByCinemaName(@PathVariable("cinemaName")String cinemaName,@PathVariable("page")int page){
        List<AreaCinemas> allByAreaName = areaCinemaSer.getCinemasByCinemaName(cinemaName,page);
        for(int i = 0;i < allByAreaName.size()-1;i++){
            CinemaMovies allByCinemaId = cinemaMoviesSer.getAllByCinemaId(allByAreaName.get(i).getId());
            if(allByCinemaId != null) {
                allByAreaName.get(i).setLawMoney(allByCinemaId.getMovieLowMoney());
                allByAreaName.get(i).setMovieId(allByCinemaId.getMovieId());
            }
        }
        return allByAreaName;
    }


    /**
     * 根据电影状态获取电影
     * @param status
     * @param page
     * @return
     */
    @GetMapping("/getMoviesByStatus/{status}/{page}")
    public List<Movie> getMoviesByStatus(@PathVariable("status")int status,@PathVariable("page")int page){
        List<Movie> movies = ByStatus(status);
        List<Movie> movieList = (List<Movie>) commonService.getPage(movies,page,25);
        movieList.add(new Movie(commonService.justPage(movies,25)));
        return movieList;
    }


    /**
     *
     * @param status 电影的状态
     * @param time   电影时间
     * @param type   电影类型
     * @param area   电影地区
     * @param page   电影页数
     * @return
     */
    @GetMapping("/getClassificationByType/{status}/{type}/{area}/{time}/{page}/{sign}")
    public List<Movie> getClassification(@PathVariable("sign")int sign,@PathVariable("status")int status,@PathVariable("time")String time
    ,@PathVariable("type")String type,@PathVariable("area")String area,@PathVariable("page")int page){
        System.out.println(1);
        List<Movie> movies = ByStatus(status);
        //按全部排序
        if("1".equals(type) && "1".equals(area) &&  "1".equals(time)){
            Collections.sort(movies, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0 ) {
                        return 1;
                    } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0) {
                        return -1;
                    } else {
                        //如果当天的钱数相同就按电影名字名字进行排序
                        return o1.getMovieName().compareTo(o2.getMovieName());
                    }
                }
            });
            List<Movie> movieList = (List<Movie>) commonService.getPage(movies,page,25);
            if(movieList == null ){
                return null;
            }
            movieList.add(new Movie(commonService.justPage(movies,25)));
            return movieList;
        }
        List<String> strings = new ArrayList<>();
        if(!"0".equals(type)){
            strings.add(type);
        }
        if(!"0".equals(area)){
            strings.add(area);
        }
        if(!"0".equals(time)){
            strings.add(time);
        }
        System.out.println(strings.toString());
        List<Movie> pattern;
        for (String string : strings) {
            if(string.endsWith("1")){
               pattern = pattern(string.substring(0,string.length()-1), movies, 1);
               System.out.println(pattern);
            }else if(string.endsWith("2")){
                pattern = pattern(string.substring(0,string.length()-1), movies, 2);
                System.out.println(pattern);
            }else {
                pattern = pattern(string.substring(0,string.length()-1), movies, 3);
                System.out.println(pattern);
            }
            movies.clear();
            movies.addAll(pattern);
        }

        List<Movie> movieList = (List<Movie>) commonService.getPage(movies,page,25);
        if(movieList == null ){
            return null;
        }
        if(sign == 1){
            Collections.sort(movieList, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0 ) {
                        return 1;
                    } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0) {
                        return -1;
                    } else {
                        //如果当天的钱数相同就按电影名字名字进行排序
                        return o1.getMovieName().compareTo(o2.getMovieName());
                    }
                }
            });
            return movieList;
        }
        movieList.add(new Movie(commonService.justPage(movies,25)));
        return movieList;
    }




    public List<Movie> pattern(String name,List<Movie> movies,int sign){
        ArrayList<Movie> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(name);
        Matcher matcher;
        for (Movie movie : movies) {
            if(sign == 1){
                 matcher = pattern.matcher(movie.getMovieType());
            }else if(sign == 2){
                matcher = pattern.matcher(movie.getMovieArea());
            }else  if(sign == 3){
                matcher = pattern.matcher(String.valueOf(movie.getMovieStart()));
            }else{
                matcher = pattern.matcher(movie.getMovieName());
            }
            if (matcher.find()) {
                results.add(movie);
            }
        }
        return results;
    }

    public List<Movie> ByStatus(int status){
        if(status == 1){
            return movieSer.getMoviesOnByRedis();
        }else if(status == 2){
            return movieSer.getMoviesSoonByRedis();
        }else{
            return movieSer.getMoviesHotByRedis();
        }
    }



}
