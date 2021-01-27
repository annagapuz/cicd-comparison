package com.example.movie.repository;

import com.example.movie.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    boolean existsMovieByTitleEquals(String title);

}
