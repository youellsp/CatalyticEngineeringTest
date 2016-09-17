package org.pyouells.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Test
    public void getIndex() throws Exception {
        String response = mainController.index();

        Assert.assertNotNull(response);
        Assert.assertEquals(response, "Catalytic Engineering Test -  " +
                "programmed by Patrick Youells");

    }

}