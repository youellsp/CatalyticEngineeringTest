package org.pyouells.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pyouells.models.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyouells on 9/16/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestHistoryControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getRequestHistory() {
        ObjectMapper mapper = new ObjectMapper();
        String server = "http://localhost:";
        String requestHistoryResponse;
        List<History> requestHistoryList = new ArrayList<>();

        String factorEndpoint = "/service/factor/";
        Long numberToFactor = 11L;
        String factorResponse =
                restTemplate.getForObject(server + port + factorEndpoint + numberToFactor, String.class);

        String requestHistoryEndpoint = "/service/requestHistory/";
        requestHistoryResponse = restTemplate.getForObject(server + port + requestHistoryEndpoint, String.class);

        try{
            requestHistoryList =  mapper.readValue(requestHistoryResponse, new TypeReference<List<History>>(){});
        }catch(JsonParseException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(requestHistoryList);
        Assert.assertEquals(requestHistoryList.get(0).getResponse(), factorResponse);

    }

    @Test
    public void getRequestHistory2() {
        ObjectMapper mapper = new ObjectMapper();
        String server = "http://localhost:";
        String requestHistoryResponse;
        List<History> requestHistoryList = new ArrayList<>();

        String palindromeEndpoint = "/service/palindrome/";
        String string = "Race Car";
        String palindromeResponse =
                restTemplate.getForObject(server + port + palindromeEndpoint + string, String.class);

        String requestHistoryEndpoint = "/service/requestHistory/";
        requestHistoryResponse = restTemplate.getForObject(server + port + requestHistoryEndpoint, String.class);

        try{
            requestHistoryList =  mapper.readValue(requestHistoryResponse, new TypeReference<List<History>>(){});
        }catch(JsonParseException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(requestHistoryList);
        Assert.assertEquals(requestHistoryList.get(0).getResponse(), palindromeResponse);

    }
}