package com.challenge.goldenraspberryawards.services;

import com.challenge.goldenraspberryawards.dtos.MovieDTO;
import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MovieIntervalCalculator {

    private ProducersRetrieval producersRetrieval;

    @Autowired
    public MovieIntervalCalculator(final ProducersRetrieval producersRetrieval) {
        this.producersRetrieval = producersRetrieval;
    }

    public List<MovieIntervalDTO> calculateWinnersByIntervalAsc() {
        final List<MovieIntervalDTO> listCalculateIntervals = new ArrayList<>();

        final Map<String, List<MovieDTO>> moviesByProducer = this.producersRetrieval.getMoviesByProducer();

        moviesByProducer.values()
                .forEach(movieList -> {
                    for (int i = 0; i < movieList.size(); i++) {
                        // Ignore producers with one win, it's unnecessary on calculate intervals
                        // and avoid calculate last item due does not have someone item to calculate interval (index out of bounds )
                        if (i + 1 >= movieList.size()) {
                            continue;
                        }
                        MovieDTO previousYear = movieList.get(i);
                        MovieDTO followingYear = movieList.get(i + 1);
                        Integer interval = followingYear.getYear() - previousYear.getYear();
                        MovieIntervalDTO intervalDTO = new MovieIntervalDTO();
                        intervalDTO.setProducer(previousYear.getProducer());
                        intervalDTO.setInterval(interval);
                        intervalDTO.setPreviousWin(previousYear.getYear());
                        intervalDTO.setFollowingWin(followingYear.getYear());
                        listCalculateIntervals.add(intervalDTO);
                    }
                });

        return listCalculateIntervals.stream().sorted().collect(Collectors.toList());
    }
}
