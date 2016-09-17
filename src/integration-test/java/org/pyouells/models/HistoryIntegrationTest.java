package org.pyouells.models;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pyouells.controllers.RequestHistoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by pyouells on 9/16/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HistoryIntegrationTest {
    @Autowired
    HistoryDao historyDao;

    @Autowired
    RequestHistoryController requestHistoryController;

    @Test
    public void historyTest(){
        History requestHistory = new History();
        List<History> historyList;

        requestHistory.setEndpoint("test-endpoint");
        requestHistory.setErrors("test-errors");
        requestHistory.setInput("test-input");
        requestHistory.setResponse("test-response");

        historyDao.save(requestHistory);

        historyList = requestHistoryController.getHistory();

        Assert.assertNotNull(historyList.get(0).getId());
        Assert.assertEquals(historyList.get(0).getResponse(), "test-response");
        Assert.assertEquals(historyList.get(0).getInput(), "test-input");
        Assert.assertEquals(historyList.get(0).getEndpoint(), "test-endpoint");
        Assert.assertEquals(historyList.get(0).getErrors(), "test-errors");
    }
}
