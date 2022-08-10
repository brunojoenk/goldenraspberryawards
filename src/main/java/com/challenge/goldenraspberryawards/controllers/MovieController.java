package com.challenge.goldenraspberryawards.controllers;

import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import com.challenge.goldenraspberryawards.services.MovieInterval;
import com.challenge.goldenraspberryawards.services.MovieIntervalCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/awards/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private MovieIntervalCalculator movieIntervalCalculator;
    private MovieInterval movieInterval;

    @Autowired
    public MovieController(final MovieIntervalCalculator movieIntervalCalculator, final MovieInterval movieInterval) {
        this.movieIntervalCalculator = movieIntervalCalculator;
        this.movieInterval = movieInterval;
    }

    @GetMapping("/min-max")
    public ResponseEntity<MinMaxDTO> get() throws ExecutionException, InterruptedException {
        final List<MovieIntervalDTO> listIntervalOrdered = movieIntervalCalculator.calculateWinnersByIntervalAsc();

        if (listIntervalOrdered.size() == 0) {
            return ResponseEntity.ok().body(new MinMaxDTO(Collections.emptyList(), Collections.emptyList()));
        }

        final CompletableFuture<List<MovieIntervalDTO>> min = movieInterval.getMin(listIntervalOrdered);
        final CompletableFuture<List<MovieIntervalDTO>> max = movieInterval.getMax(listIntervalOrdered);

        CompletableFuture.allOf(min, max).join();

        return ResponseEntity.ok().body(new MinMaxDTO(min.get(), max.get()));
    }

    record MinMaxDTO(List<MovieIntervalDTO> min, List<MovieIntervalDTO> max) {
    }

}
