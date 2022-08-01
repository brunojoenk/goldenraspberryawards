package com.challenge.goldenraspberryawards.services;

import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import com.challenge.goldenraspberryawards.dtos.MovieMinMaxIntervalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MovieIntervalMinMaxBuilder {

    private MovieIntervalCalculator movieIntervalCalculator;

    @Autowired
    public MovieIntervalMinMaxBuilder(final MovieIntervalCalculator movieIntervalCalculator) {
        this.movieIntervalCalculator = movieIntervalCalculator;
    }

    public MovieMinMaxIntervalDTO build() {
        final List<MovieIntervalDTO> listIntervalOrdered = movieIntervalCalculator.calculateWinnersByIntervalAsc();

        final Integer minInterval = listIntervalOrdered.get(0).getInterval();
        final Integer maxInterval = listIntervalOrdered.get(listIntervalOrdered.size() - 1).getInterval();

        final MovieMinMaxIntervalDTO movieMinMaxIntervalDTO = new MovieMinMaxIntervalDTO();

        for (MovieIntervalDTO movieIntervalDTO : listIntervalOrdered) {
            if (Objects.equals(movieIntervalDTO.getInterval(), minInterval))
                movieMinMaxIntervalDTO.getMin().add(movieIntervalDTO);
            if (Objects.equals(movieIntervalDTO.getInterval(), maxInterval))
                movieMinMaxIntervalDTO.getMax().add(movieIntervalDTO);
        }

        return movieMinMaxIntervalDTO;
    }
}
