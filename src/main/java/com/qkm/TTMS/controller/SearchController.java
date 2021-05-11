package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.MovieService;
import com.qkm.TTMS.service.impl.AreaCinemaServiceImpl;
import com.qkm.TTMS.service.impl.CinemaMoviesServiceImpl;
import com.qkm.TTMS.service.impl.MovieServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SearchController {

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
     * @param movieName
     * @return
     */
    @GetMapping("/searchMoviesByName")
    public List<Movie> getMovies(@RequestParam("movieName")String movieName){
        List<Movie> movies = movieSer.getMovies();
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
        List<AreaCinemas> allByAreaName = areaCinemaSer.getAllByAreaName(cinemaName);
        for (AreaCinemas areaCinemas : allByAreaName) {
            CinemaMovies allByCinemaId = cinemaMoviesSer.getAllByCinemaId(areaCinemas.getId());
            areaCinemas.setLawMoney(allByCinemaId.getMovieLowMoney());
            areaCinemas.setMovieId(allByCinemaId.getMovieId());
        }
        return allByAreaName;
    }
}
