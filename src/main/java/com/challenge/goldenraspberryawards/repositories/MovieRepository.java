package com.challenge.goldenraspberryawards.repositories;

import com.challenge.goldenraspberryawards.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinnerTrueOrderByYearAsc();
}
