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
public class FactorControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getFactorTest() {
        String server = "http://localhost:";
        String endpoint = "/service/factor/";
        Long numberToFactor = 5L;

        String response =
                restTemplate.getForObject(server + port + endpoint + numberToFactor, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "The factors of 5 are [1, 5]");

    }

    @Test
    public void getFactorBadRequestTest() {
        String server = "http://localhost:";
        String endpoint = "/service/factor/";
        String numberToFactor = "five";

        String response =
                restTemplate.getForObject(server + port + endpoint + numberToFactor, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "An error occurred while factoring the input: \"five\". " +
                "(number should be of type java.lang.Long). Please check \"errors\" in the history table " +
                "for the exception message.");

    }
}