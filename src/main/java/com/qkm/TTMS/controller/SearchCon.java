package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import com.qkm.TTMS.service.impl.CinemaMoviesSerImpl;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SearchCon {

    private final CinemaMoviesSerImpl cinemaMoviesSer;
    private final AreaCinemaSerImpl areaCinemaSerImpl;
    private final MovieSerImpl movieSerImpl;

    public SearchCon(MovieSerImpl movieSerImpl, AreaCinemaSerImpl areaCinemaSerImpl, CinemaMoviesSerImpl cinemaMoviesSer) {
        this.movieSerImpl = movieSerImpl;
        this.areaCinemaSerImpl = areaCinemaSerImpl;
        this.cinemaMoviesSer = cinemaMoviesSer;
    }

    /**
     * 按照电影名字查询,查询出包含该关键字的
     * @param movieName
     * @return
     */
    @GetMapping("/searchMoviesByName")
    public List<Movie> getMovies(@RequestParam("movieName")String movieName){
        List<Movie> movies = movieSerImpl.getMovies();
        ArrayList<Movie> results = new ArrayList<Movie>();
        Pattern pattern = Pattern.compile(movieName);
        for(int i=0; i < movies.size(); i++) {
            Matcher matcher = pattern.matcher(movies.get(i).getMovieName());
            if (matcher.find()) {
                results.add(movies.get(i));
            }
        }
        return results;
    }


    /**
     * 根据影院名字查影院
     * @param cinemaName
     * @return
     */
    @GetMapping("/searchCinemasByName")
    public List<AreaCinemas> getCinemas(@RequestParam("cinemaName")String cinemaName){
        List<AreaCinemas> allByAreaName = areaCinemaSerImpl.getAllByAreaName(cinemaName);
        for (AreaCinemas areaCinemas : allByAreaName) {
            CinemaMovies allByCinemaId = cinemaMoviesSer.getAllByCinemaId(areaCinemas.getId());
            areaCinemas.setLawMoney(allByCinemaId.getMovieLowMoney());
            areaCinemas.setMovieId(allByCinemaId.getMovieId());
        }
        return allByAreaName;
    }
}
