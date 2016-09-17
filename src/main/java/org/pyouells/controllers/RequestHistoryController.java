package org.pyouells.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.pyouells.models.History;
import org.pyouells.models.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Created by pyouells on 9/10/16.
 */
@RestController
public class RequestHistoryController {

    @ApiOperation(value = "Retrieves all the requests that have been made by the user")
    @RequestMapping(value = "service/requestHistory", method = RequestMethod.GET)
    @ApiResponse(code = 200, message = "Success", response = List.class)
    public List<History> getHistory() {

        List<History> requestHistory = new ArrayList<>();
        historyDao.findAll().forEach(history -> requestHistory.add(history));

        //sorts request history to display the most recent requests first
        requestHistory.sort(Comparator.comparing((History c) -> c.getId()).reversed());

        return  requestHistory;
    }

    @Autowired
    private HistoryDao historyDao;

}