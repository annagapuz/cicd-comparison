package com.example.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private int streamCount;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
        this.streamCount = 0;
    }

    public Movie(String title, int streamCount) {
        this.title = title;
        this.streamCount = streamCount;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStreamCount() {
        return streamCount;
    }
}
