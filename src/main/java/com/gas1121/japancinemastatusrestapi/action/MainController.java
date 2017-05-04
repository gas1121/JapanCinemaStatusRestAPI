package com.gas1121.japancinemastatusrestapi.action;

import com.gas1121.japancinemastatusrestapi.persist.movie.Movie;
import com.gas1121.japancinemastatusrestapi.persist.movie.MovieRepository;
import com.gas1121.japancinemastatusrestapi.persist.showing.ShowingRepository;

import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

import java.util.List;

@RestController
public class MainController {

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(path="/api/movie", produces = CONTENT_TYPE)
    public  @ResponseBody String movie() {
        logger.info("get /api/movie");
        List<Movie> result = movieRepository.findAll();
        JSONObject output = new JSONObject();
        JSONArray movies = new JSONArray();
        for (Movie curr: result) {
            JSONObject movie = new JSONObject();
            movie.put("id", curr.getId());
            movie.put("title", curr.getTitle());
            movies.add(movie);
        }
        output.put("movies", movies);
        return output.toString();
    }

    @GetMapping(path="/api/showing/seats", produces = CONTENT_TYPE)
    public @ResponseBody String showing() {
        logger.info("get /api/showing/seats");
        Iterable<Object[]> result = showingRepository.getAllData();
        JSONObject output = new JSONObject();
        JSONArray movies = new JSONArray();
        for (Object[] curr: result) {
            JSONObject movie = new JSONObject();
            movie.put("title", curr[0].toString());
            movie.put("total", curr[1].toString());
            movies.add(movie);
        }
        output.put("movies", movies);
        return output.toString();
    }

    @GetMapping(path="/api/showing/seats/cinema", produces = CONTENT_TYPE)
    public @ResponseBody String cinema() {
        logger.info("get /api/showing/seats/cinema");
        Iterable<Object[]> result = showingRepository.getAllDataDividedByCinemaName();
        JSONObject output = new JSONObject();
        JSONArray movies = new JSONArray();
        for (Object[] curr: result) {
            JSONObject movie = new JSONObject();
            movie.put("title", curr[0].toString());
            movie.put("cinema_name", curr[2].toString());
            movie.put("total", curr[1].toString());
            movies.add(movie);
        }
        output.put("movies", movies);
        return output.toString();
    }
}