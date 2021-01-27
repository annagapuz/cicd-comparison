package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMoviesByStreamCount() {
        return ResponseEntity.ok(movieService.getAllMoviesByStreamCountDesc());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addMovieTitle(Movie movie) {
        boolean isAdded = movieService.addMovieTitle(Optional.ofNullable(movie).map(Movie::getTitle).map(String::toUpperCase).orElse(""));
        return isAdded ?
                ResponseEntity.ok(Map.of("status", "ADDED")) :
                ResponseEntity.badRequest().body(Map.of("status", "EXISTS"));
    }
}
