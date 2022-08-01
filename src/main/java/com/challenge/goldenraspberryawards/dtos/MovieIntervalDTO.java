package com.challenge.goldenraspberryawards.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieIntervalDTO implements Comparable<MovieIntervalDTO>{

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    @Override
    public int compareTo(MovieIntervalDTO o) {
        if(this.interval==o.getInterval())
            return 0;
        else if(this.interval>o.getInterval())
            return 1;
        else
            return -1;
    }
}
