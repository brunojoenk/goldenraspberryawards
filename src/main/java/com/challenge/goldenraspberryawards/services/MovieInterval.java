package com.challenge.goldenraspberryawards.services;

import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class MovieInterval {

    private static Logger LOG = LoggerFactory.getLogger(MovieInterval.class);

    @Async
    public CompletableFuture<List<MovieIntervalDTO>> getMin(final List<MovieIntervalDTO> listIntervalOrdered) {
        final Integer minInterval = listIntervalOrdered.get(0).getInterval();

        return CompletableFuture.completedFuture(listIntervalOrdered.stream()
                .filter(intervalDTO -> {
                    LOG.info("Testing thread async (min)");
                    return Objects.equals(intervalDTO.getInterval(), minInterval);
                }).collect(Collectors.toList()));
    }

    @Async
    public CompletableFuture<List<MovieIntervalDTO>> getMax(final List<MovieIntervalDTO> listIntervalOrdered) {
        final Integer maxInterval = listIntervalOrdered.get(listIntervalOrdered.size() - 1).getInterval();

        return CompletableFuture.completedFuture(listIntervalOrdered.stream()
                .filter(intervalDTO -> {
                    LOG.info("Testing thread async (max)");
                    return Objects.equals(intervalDTO.getInterval(), maxInterval);
                }).collect(Collectors.toList()));
    }
}
