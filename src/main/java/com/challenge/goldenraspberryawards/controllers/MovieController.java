package com.challenge.goldenraspberryawards.controllers;

import com.challenge.goldenraspberryawards.dtos.MovieMinMaxIntervalDTO;
import com.challenge.goldenraspberryawards.services.MovieIntervalMinMaxBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/awards/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    private MovieIntervalMinMaxBuilder movieIntervalMinMaxBuilder;

    @Autowired
    public MovieController(final MovieIntervalMinMaxBuilder movieIntervalMinMaxBuilder) {
        this.movieIntervalMinMaxBuilder = movieIntervalMinMaxBuilder;
    }

    @GetMapping("/min-max")
    public MovieMinMaxIntervalDTO get() {
        return movieIntervalMinMaxBuilder.build();
    }

}
