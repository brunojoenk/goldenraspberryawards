package com.challenge.goldenraspberryawards.mappers;

import com.challenge.goldenraspberryawards.dtos.MovieCSV;
import com.challenge.goldenraspberryawards.dtos.MovieDTO;
import com.challenge.goldenraspberryawards.entities.Movie;

public class MovieMapper {

    public static MovieDTO toDTO(Movie movie) {
        return MovieDTO.builder()
                .year(movie.getYear())
                .producer(movie.getProducer())
                .studio(movie.getStudio())
                .title(movie.getTitle())
                .winner(movie.getWinner())
                .build();
    }

    public static Movie toEntity(MovieCSV movieCSV) {
        return Movie.builder()
                .year(movieCSV.getYear())
                .producer(movieCSV.getProducer())
                .studio(movieCSV.getStudio())
                .title(movieCSV.getTitle())
                .winner(movieCSV.getWinner())
                .build();
    }
}
