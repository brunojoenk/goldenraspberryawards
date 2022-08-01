package com.challenge.goldenraspberryawards.services;

import com.challenge.goldenraspberryawards.dtos.MovieDTO;
import com.challenge.goldenraspberryawards.entities.Movie;
import com.challenge.goldenraspberryawards.mappers.MovieMapper;
import com.challenge.goldenraspberryawards.repositories.MovieRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProducersRetrieval {

    private static final String REGEX_WHEN_HAS_MORE_THAN_ONE_PRODUCER = "(\\,)|( and )";

    private final MovieRepository movieRepository;

    @Autowired
    public ProducersRetrieval(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Map<String, List<MovieDTO>> getMoviesByProducer() {

        final List<Movie> movies = movieRepository.findByWinnerTrueOrderByYearAsc();
        final List<MovieDTO> movieDTOS = new ArrayList<>();

        movies.stream()
                .map(MovieMapper::toDTO)
                .forEach(movieDTO -> {
                    String[] producers = movieDTO.getProducer().split(REGEX_WHEN_HAS_MORE_THAN_ONE_PRODUCER);
                    for (String producerName : producers) {
                        if (!producerName.trim().equals(Strings.EMPTY)) {
                            MovieDTO movieResponseAfterSplit = new MovieDTO();
                            movieResponseAfterSplit.setProducer(producerName.trim());
                            movieResponseAfterSplit.setYear(movieDTO.getYear());
                            movieResponseAfterSplit.setStudio(movieDTO.getStudio());
                            movieResponseAfterSplit.setTitle(movieDTO.getTitle());
                            movieResponseAfterSplit.setWinner(movieDTO.getWinner());
                            movieDTOS.add(movieResponseAfterSplit);
                        }
                    }
                });

        return movieDTOS.stream()
                .collect(Collectors.groupingBy(MovieDTO::getProducer));
    }

}
