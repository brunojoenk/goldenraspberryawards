package com.challenge.goldenraspberryawards.configurations;

import com.challenge.goldenraspberryawards.dtos.MovieCSV;
import com.challenge.goldenraspberryawards.mappers.MovieMapper;
import com.challenge.goldenraspberryawards.repositories.MovieRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
            final File file = ResourceUtils.getFile("classpath:movielist.csv");
            List<MovieCSV> movieCSVList = new CsvToBeanBuilder(new FileReader(file))
                    .withSeparator(';')
                    .withType(MovieCSV.class)
                    .build()
                    .parse();

            movieCSVList.forEach(movieCSV -> movieRepository.save(MovieMapper.toEntity(movieCSV)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
