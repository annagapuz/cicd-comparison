package com.example.movie.service;

import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

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

    public int incrementStreamCountById(long movieId, int incrementCount) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        movie.ifPresent(m -> {
            m.setStreamCount(m.getStreamCount() + incrementCount);
            movieRepository.save(m);
        });
        return movie.map(Movie::getStreamCount).orElse(-1);
    }
}
