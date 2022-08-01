package com.challenge.goldenraspberryawards.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieMinMaxIntervalDTO {

    private List<MovieIntervalDTO> min = new ArrayList<>();
    private List<MovieIntervalDTO> max = new ArrayList<>();

}
