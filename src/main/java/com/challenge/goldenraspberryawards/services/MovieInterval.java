package com.challenge.goldenraspberryawards.services;

import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class MovieInterval {

    @Async
    public CompletableFuture<List<MovieIntervalDTO>> getMin(final List<MovieIntervalDTO> listIntervalOrdered) {
        final Integer minInterval = listIntervalOrdered.get(0).getInterval();

        return CompletableFuture.completedFuture(listIntervalOrdered.stream()
                .filter(intervalDTO -> Objects.equals(intervalDTO.getInterval(), minInterval))
                .collect(Collectors.toList()));
    }

    @Async
    public CompletableFuture<List<MovieIntervalDTO>> getMax(final List<MovieIntervalDTO> listIntervalOrdered) {
        final Integer maxInterval = listIntervalOrdered.get(listIntervalOrdered.size() - 1).getInterval();

        return CompletableFuture.completedFuture(listIntervalOrdered.stream()
                .filter(intervalDTO -> Objects.equals(intervalDTO.getInterval(), maxInterval))
                .collect(Collectors.toList()));
    }
}
