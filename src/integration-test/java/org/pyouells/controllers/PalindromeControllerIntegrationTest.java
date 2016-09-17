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
public class PalindromeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getPalindrome() {
        String server = "http://localhost:";
        String endpoint = "/service/palindrome/";
        String palindromeToTest = "Draw pupil's pup's lip upward";

        String response =
                restTemplate.getForObject(server + port + endpoint + palindromeToTest, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "'Draw pupil's pup's lip upward' is a Palindrome");

    }

    @Test
    public void getPalindrome2() {
        String server = "http://localhost:";
        String endpoint = "/service/palindrome/";
        String palindromeToTest = "Draw pupil's pup's lip downward";

        String response =
                restTemplate.getForObject(server + port + endpoint + palindromeToTest, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "'Draw pupil's pup's lip downward' is not a Palindrome");

    }
}