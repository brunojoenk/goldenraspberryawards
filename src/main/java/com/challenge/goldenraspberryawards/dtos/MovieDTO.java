package com.challenge.goldenraspberryawards.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Integer year;
    private String title;
    private String studio;
    private String producer;
    private Boolean winner;

}
