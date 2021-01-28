package com.example.movie.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private String title;
    private Integer streamCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStreamCount() {
        return streamCount;
    }

    public void setStreamCount(Integer streamCount) {
        this.streamCount = streamCount;
    }
}
