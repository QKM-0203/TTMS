package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SearchController {

    private final int TYPE = 1;
    private final int AREA = 2;
    private final int TIME = 3;
    private final int NAME = 4;

    private final CinemaMoviesService cinemaMoviesSer;
    private final AreaCinemaService areaCinemaSer;
    private final MovieService movieSer;

    public SearchController(MovieService movieSer, AreaCinemaService areaCinemaSer, CinemaMoviesService cinemaMoviesSer) {
        this.movieSer = movieSer;
        this.areaCinemaSer = areaCinemaSer;
        this.cinemaMoviesSer = cinemaMoviesSer;
    }

    /**
     * 按照电影名字查询,查询出包含该关键字的
     * @param movieName  电影名字
     * @return 所有的电影信息
     */
    @GetMapping("/searchMoviesByName/{movieName}")
    public List<Movie> getMovies(@PathVariable("movieName")String movieName){
        List<Movie> movies = movieSer.getMovies();
        return pattern(movieName,movies,4);
    }


    /**
     * 根据影院名字查影院
     * @param cinemaName   电影院的名字
     * @return  所有的电影院信息
     */
    @GetMapping("/searchCinemasByName/{cinemaName}")
    public List<AreaCinemas> getCinemas(@PathVariable("cinemaName")String cinemaName){
        List<AreaCinemas> allByAreaName = areaCinemaSer.getAllByAreaName(cinemaName);
        for (AreaCinemas areaCinemas : allByAreaName) {
            CinemaMovies allByCinemaId = cinemaMoviesSer.getAllByCinemaId(areaCinemas.getId());
            areaCinemas.setLawMoney(allByCinemaId.getMovieLowMoney());
            areaCinemas.setMovieId(allByCinemaId.getMovieId());
        }
        return allByAreaName;
    }

    @GetMapping("/getClassificationByType/{status}/{name}")
    public List<Movie> getClassification(@PathVariable("status")int status,@PathVariable("name")String name){
        if(status == 1){
            List<Movie> moviesOnByRedis = movieSer.getMoviesOnByRedis();
            return pattern(name,moviesOnByRedis,1);
        }else if(status == 2){
            List<Movie> moviesSoonByRedis = movieSer.getMoviesSoonByRedis();
            return pattern(name,moviesSoonByRedis,1);
        }else{
            List<Movie> moviesHotByRedis = movieSer.getMoviesHotByRedis();
            return pattern(name,moviesHotByRedis,1);
        }

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

}
