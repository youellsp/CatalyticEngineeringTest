package org.pyouells.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by pyouells on 9/16/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getIndex() {
        String server = "http://localhost:";
        String endpoint = "/";

        String response =
                restTemplate.getForObject(server + port + endpoint, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "Catalytic Engineering Test -  " +
                "programmed by Patrick Youells");

    }

    @Test
    public void getIndex2() {
        String server = "http://localhost:";
        String endpoint = "";

        String response =
                restTemplate.getForObject(server + port + endpoint, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "Catalytic Engineering Test -  " +
                "programmed by Patrick Youells");

    }

}