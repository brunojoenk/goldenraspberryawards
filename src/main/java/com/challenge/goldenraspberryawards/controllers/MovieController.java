package com.challenge.goldenraspberryawards.controllers;

import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import com.challenge.goldenraspberryawards.services.MovieIntervalCalculator;
import com.challenge.goldenraspberryawards.services.MovieInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/awards/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private MovieIntervalCalculator movieIntervalCalculator;

    @Autowired
    public MovieController(final MovieIntervalCalculator movieIntervalCalculator) {
        this.movieIntervalCalculator = movieIntervalCalculator;
    }

    @GetMapping("/min-max")
    public ResponseEntity<MinMaxDTO> get() {
        final List<MovieIntervalDTO> listIntervalOrdered = movieIntervalCalculator.calculateWinnersByIntervalAsc();

        if (listIntervalOrdered.size() == 0) {
            return ResponseEntity.ok().body(new MinMaxDTO(Collections.emptyList(), Collections.emptyList()));
        }

        return ResponseEntity.ok().body(new MinMaxDTO(MovieInterval.getMin(listIntervalOrdered), MovieInterval.getMax(listIntervalOrdered)));
    }

    record MinMaxDTO(List<MovieIntervalDTO> min, List<MovieIntervalDTO> max) {}

}
