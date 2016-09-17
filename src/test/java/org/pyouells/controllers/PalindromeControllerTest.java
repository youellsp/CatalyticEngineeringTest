package org.pyouells.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PalindromeControllerTest {

    @Autowired
    private PalindromeController palindromeController;

    @Mock
    HttpServletRequest request;

    @Test
    public void palindromeTest() throws Exception {
        String response = palindromeController.getPalindrome("Race car", request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "'Race car' is a Palindrome");

    }

    @Test
    public void palindromeTest2() throws Exception {
        String response = palindromeController.getPalindrome("Race cars", request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "'Race cars' is not a Palindrome");

    }
}