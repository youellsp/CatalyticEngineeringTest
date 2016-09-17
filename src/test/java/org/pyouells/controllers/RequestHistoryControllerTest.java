package org.pyouells.controllers;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.pyouells.models.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RequestHistoryControllerTest {

    @Autowired
    private RequestHistoryController requestHistoryController;

    @Autowired
    private FactorController factorController;

    @Mock
    HttpServletRequest request;


    @Test
    public void getHistoryTestMostRecent() throws Exception {
        String factorResponse = factorController.getFactors(11L, request);
        List<History> response = requestHistoryController.getHistory();

        Assert.assertNotNull(response);
        Assert.assertNotNull(factorResponse);

        History lastHistoryEntry = response.get(0);
        Assert.assertEquals(lastHistoryEntry.getResponse(), "The factors of 11 are [1, 11]");
    }

    @Test
    public void getHistoryTestMostRecent2() throws Exception {
        String factorResponse = factorController.getFactors(12L, request);
        List<History> response = requestHistoryController.getHistory();

        Assert.assertNotNull(response);
        Assert.assertNotNull(factorResponse);

        History lastHistoryEntry = response.get(0);
        Assert.assertEquals(lastHistoryEntry.getResponse(), "The factors of 12 are [1, 2, 3, 4, 6, 12]");
    }
}