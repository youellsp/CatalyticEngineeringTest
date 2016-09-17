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
public class FactorControllerTest {

    @Autowired
    private FactorController factorController;

    @Mock
    HttpServletRequest request;

    @Test
    public void getFactorTest() throws Exception {
        String response = factorController.getFactors(11L, request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "The factors of 11 are [1, 11]");

    }

    @Test
    public void getFactorsTest2() throws Exception {
        String response = factorController.getFactors(12L, request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "The factors of 12 are [1, 2, 3, 4, 6, 12]");

    }
}