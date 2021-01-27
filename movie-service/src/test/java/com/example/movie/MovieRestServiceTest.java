package com.example.movie;

import com.example.movie.controller.MovieController;
import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieRestServiceTest {

    @Test
    void getAllMovies() {
        List<Movie> testMovies = new ArrayList<>();
        testMovies.add(new Movie("UP", 5));
        testMovies.add(new Movie("INSIDE OUT", 1));
        testMovies.add(new Movie("COCO", 30));

        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.findAll()).thenReturn(testMovies);

        MovieService movieService = new MovieService(movieRepository);

        MovieController movieController = new MovieController(movieService);

        List<Movie> movies = movieController.getMoviesByStreamCount().getBody();
        assertThat(movies.get(0),
                allOf(hasProperty("title", is("COCO")),
                        hasProperty("streamCount", is(30))));
        assertThat(movies.get(1),
                allOf(hasProperty("title", is("UP")),
                        hasProperty("streamCount", is(5))));
        assertThat(movies.get(2),
                allOf(hasProperty("title", is("INSIDE OUT")),
                        hasProperty("streamCount", is(1))));
    }

    @Test
    void addMovieButAlreadyExists() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.existsMovieByTitleEquals("UP")).thenReturn(true);

        MovieService movieService = new MovieService(movieRepository);

        MovieController movieController = new MovieController(movieService);

        Map<String, String> returnStatus = movieController.addMovieTitle(new Movie("UP")).getBody();
        assertThat(returnStatus.get("status"), is("EXISTS"));
    }

    @Test
    void addMovieThatDoesntExist() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.existsMovieByTitleEquals("ONWARD")).thenReturn(false);

        MovieService movieService = new MovieService(movieRepository);

        MovieController movieController = new MovieController(movieService);

        Map<String, String> returnStatus = movieController.addMovieTitle(new Movie("ONWARD")).getBody();
        assertThat(returnStatus.get("status"), is("ADDED"));
    }
}