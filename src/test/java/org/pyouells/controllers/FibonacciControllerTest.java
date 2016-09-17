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
public class FibonacciControllerTest {

    @Autowired
    private FibonacciController fibonacciController;

    @Mock
    HttpServletRequest request;

    @Test
    public void getFibonacciSequence() throws Exception {
        String response = fibonacciController.getFibonacciValues(13, request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "The fibonacci series of 13 is: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233");
    }

    @Test
    public void getFibonacciSequence2() throws Exception {
        String response = fibonacciController.getFibonacciValues(15, request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "The fibonacci series of 15 is: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610");
    }
}