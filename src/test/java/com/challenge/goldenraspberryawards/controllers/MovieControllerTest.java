package com.challenge.goldenraspberryawards.controllers;

import com.challenge.goldenraspberryawards.GoldenRaspberryAwardsApplication;
import com.challenge.goldenraspberryawards.dtos.MovieMinMaxIntervalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GoldenRaspberryAwardsApplication.class)
public class MovieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSuccess() {
        ResponseEntity<MovieMinMaxIntervalDTO> responseRetrieval = restTemplate
                .getForEntity("/awards/movies/min-max", MovieMinMaxIntervalDTO.class);

        assertThat(responseRetrieval.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(responseRetrieval.getBody().getMin().size(), equalTo(1));
        assertThat(responseRetrieval.getBody().getMin().get(0).getProducer(), equalTo("Joel Silver"));
        assertThat(responseRetrieval.getBody().getMin().get(0).getInterval(), equalTo(1));
        assertThat(responseRetrieval.getBody().getMin().get(0).getPreviousWin(), equalTo(1990));
        assertThat(responseRetrieval.getBody().getMin().get(0).getFollowingWin(), equalTo(1991));

        assertThat(responseRetrieval.getBody().getMax().get(0).getProducer(), equalTo("Matthew Vaughn"));
        assertThat(responseRetrieval.getBody().getMax().get(0).getInterval(), equalTo(13));
        assertThat(responseRetrieval.getBody().getMax().get(0).getPreviousWin(), equalTo(2002));
        assertThat(responseRetrieval.getBody().getMax().get(0).getFollowingWin(), equalTo(2015));

    }

    @Test
    public void testNotFoundUri() {
        ResponseEntity<MovieMinMaxIntervalDTO> responseRetrieval = restTemplate
                .getForEntity("/awards/notfound", MovieMinMaxIntervalDTO.class);

        assertThat(responseRetrieval.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));

    }

    @Test
    public void testIncorrectMethod() {
        ResponseEntity<MovieMinMaxIntervalDTO> responseRetrieval = restTemplate
                .postForEntity("/awards/movies/min-max", MovieMinMaxIntervalDTO.class, MovieMinMaxIntervalDTO.class);

        assertThat(responseRetrieval.getStatusCode(), equalTo(HttpStatus.METHOD_NOT_ALLOWED));

    }

}
