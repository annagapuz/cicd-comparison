package com.example.movie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.web.reactive.function.client.WebClient.builder;

@Service
public class MovieServiceRestClient {

    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceRestClient.class);

    private WebClient webClient;

    @Autowired
    public MovieServiceRestClient(@Value("${movie.service.url}") String movieServiceUrl) {
        LOG.info("Creating WebClient to URL {}", movieServiceUrl);
        this.webClient = builder()
                .baseUrl(movieServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<Movie> getMovies() {
        return this.webClient.get().retrieve().toEntityList(Movie.class).block().getBody();
    }
}
