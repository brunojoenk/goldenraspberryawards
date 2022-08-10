package com.challenge.goldenraspberryawards.services;

import com.challenge.goldenraspberryawards.dtos.MovieIntervalDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieInterval {

    public static List<MovieIntervalDTO> getMin(final List<MovieIntervalDTO> listIntervalOrdered) {
        final Integer minInterval = listIntervalOrdered.get(0).getInterval();

        return listIntervalOrdered.stream()
                .filter(intervalDTO -> Objects.equals(intervalDTO.getInterval(), minInterval))
                .collect(Collectors.toList());
    }

    public static List<MovieIntervalDTO> getMax(final List<MovieIntervalDTO> listIntervalOrdered) {
        final Integer maxInterval = listIntervalOrdered.get(listIntervalOrdered.size() - 1).getInterval();

        return listIntervalOrdered.stream()
                .filter(intervalDTO -> Objects.equals(intervalDTO.getInterval(), maxInterval))
                .collect(Collectors.toList());
    }
}
