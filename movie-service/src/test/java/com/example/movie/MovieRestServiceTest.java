package com.example.movie;

import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MovieRestServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MovieService movieService;

    @MockBean
    MovieRepository movieRepository;

    @Test
    void getMoviesByStreamCount() throws Exception {
        List<Movie> testMovies = new ArrayList<>();
        testMovies.add(new Movie("UP", 5));
        testMovies.add(new Movie("INSIDE OUT", 1));
        testMovies.add(new Movie("COCO", 30));

        when(movieRepository.findAll()).thenReturn(testMovies);

        this.mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(0))
                .andExpect(jsonPath("$[0].title").value("COCO"))
                .andExpect(jsonPath("$[0].streamCount").value(30))
                .andExpect(jsonPath("$[1].id").value(0))
                .andExpect(jsonPath("$[1].title").value("UP"))
                .andExpect(jsonPath("$[1].streamCount").value(5))
                .andExpect(jsonPath("$[2].id").value(0))
                .andExpect(jsonPath("$[2].title").value("INSIDE OUT"))
                .andExpect(jsonPath("$[2].streamCount").value(1));
    }

    @Test
    void addMovieTitleThatAlreadyExists() throws Exception {
        when(movieRepository.existsMovieByTitleEquals("UP")).thenReturn(true);
        this.mockMvc.perform(post("/movies")
                .content("{\"title\" : \"up\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("EXISTS"));
        verify(movieRepository).existsMovieByTitleEquals("UP");
    }

    @Test
    void addMovieTitleThatDoesNotExist() throws Exception {
        when(movieRepository.existsMovieByTitleEquals("COCO")).thenReturn(false);
        this.mockMvc.perform(post("/movies")
                .content("{\"title\" : \"coco\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ADDED"));
        verify(movieRepository).existsMovieByTitleEquals("COCO");
    }

    @Test
    void incrementStreamCountOfExistingMovie() throws Exception {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(new Movie("UP", 15)));
        this.mockMvc.perform(put("/movies/1/streamCount/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.newCount").value(20));
        verify(movieRepository).findById(1L);
    }

    @Test
    void incrementStreamCountOfMissingMovie() throws Exception {
        when(movieRepository.findById(7L)).thenReturn(Optional.empty());
        this.mockMvc.perform(put("/movies/7/streamCount/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.newCount").value(-1));
        verify(movieRepository).findById(7L);
    }
}