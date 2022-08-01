package com.challenge.goldenraspberryawards.configurations;

import com.challenge.goldenraspberryawards.dtos.MovieCSV;
import com.challenge.goldenraspberryawards.mappers.MovieMapper;
import com.challenge.goldenraspberryawards.repositories.MovieRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;


@Configuration
public class DataLoaderConfig {
    private final MovieRepository movieRepository;

    @Autowired
    public DataLoaderConfig(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        try {
            final InputStream in = getClass().getResourceAsStream("/movielist.csv");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            final List<MovieCSV> movieCSVList = new CsvToBeanBuilder(reader)
                    .withSeparator(';')
                    .withType(MovieCSV.class)
                    .build()
                    .parse();

            movieCSVList.forEach(movieCSV -> movieRepository.save(MovieMapper.toEntity(movieCSV)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
