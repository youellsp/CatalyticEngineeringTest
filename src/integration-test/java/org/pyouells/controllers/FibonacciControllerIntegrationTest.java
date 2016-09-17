package org.pyouells.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FibonacciControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getFibonacciSequence() {
        String server = "http://localhost:";
        String endpoint = "/service/fibonacci/";
        Long numberToGetFibonacci = 12L;

        String response =
                restTemplate.getForObject(server + port + endpoint + numberToGetFibonacci, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "The fibonacci series of 12 is: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144");

    }

    @Test
    public void getFibonacciSequenceBadRequest() {
        String server = "http://localhost:";
        String endpoint = "/service/fibonacci/";
        String numberToGetFibonacci = "twelve";

        String response =
                restTemplate.getForObject(server + port + endpoint + numberToGetFibonacci, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "An error occurred while getting the fibonacci sequence for the " +
                "input: \"twelve\". (number should be of type java.lang.Integer). Please check \"errors\" " +
                "in the history table for the exception message.");

    }
}