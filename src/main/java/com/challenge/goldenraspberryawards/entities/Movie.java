package com.challenge.goldenraspberryawards.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "release_year")
    private Integer year;
    private String title;
    private String studio;
    private String producer;
    private Boolean winner;

    public Movie(Integer year, String title, String studio, String producer, Boolean winner) {
        this.year = year;
        this.title = title;
        this.studio = studio;
        this.producer = producer;
        this.winner = winner;
    }
}
