package com.example.movie.service;

import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMoviesByStreamCountDesc() {
        List<Movie> allMovies = new ArrayList<>();
        movieRepository.findAll().forEach(allMovies::add);
        allMovies.sort(Comparator.comparing(Movie::getStreamCount).reversed());
        return allMovies;
    }

    public boolean addMovieTitle(String title) {
        if (movieRepository.existsMovieByTitleEquals(title)) {
            return false;
        }
        movieRepository.save(new Movie(title));
        return true;
    }
}
