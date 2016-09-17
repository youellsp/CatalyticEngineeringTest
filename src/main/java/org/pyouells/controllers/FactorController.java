package org.pyouells.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.pyouells.models.History;
import org.pyouells.models.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pyouells on 9/10/16.
 */
@RestController
public class FactorController {

    @ApiOperation(value = "Pass in a number and factor it")
    @RequestMapping(value = "service/factor/{number}", method = RequestMethod.GET)
    @ApiResponse(code = 200, message = "Success", response = String.class)
    public String getFactors(@PathVariable Long number, HttpServletRequest request) throws Exception{
        History requestHistory;
        String path = "service/factor/";
        String output;

        try {
            int incrementer = 1;
            if (number % 2 != 0)
            {
                incrementer = 2; //only test the odd ones
            }
            List<Long> factors = new ArrayList<>();
            for (long i = 1; i <= number / 2; i=i+incrementer)
            {
                if (number % i == 0)
                {
                    factors.add(i);
                }
            }
            factors.add(number);

            output = "The factors of " + number + " are " + factors.toString();

            //Create a new history object to be saved to the database
            requestHistory = new History(path, number.toString(), output, null);
            historyDao.save(requestHistory);

            return requestHistory.getResponse();
        } catch (RuntimeException e) {
            output = "An error occurred while factoring the input: " + number.toString() +
                    " please check 'errors' in the history table.";

            System.out.println(e);
            //Create a new history object to be saved to the database
            requestHistory = new History(path, number.toString(), output, e.getMessage());
            historyDao.save(requestHistory);

           return requestHistory.getResponse();
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception exception) {
        String error = exception.getMessage();

        //Get user input
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String input = pathVariables.get("number").toString();

        History requestHistory;
        String path = request.getServletPath();
        String output = "An error occurred while factoring the input: \"" + input +
                "\". Please check \"errors\" in the history table.";

        //Create a new history object to be saved to the database
        requestHistory = new History(path, input, output, error);
        historyDao.save(requestHistory);

        return output;
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public String handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String error = "(" + ex.getName() + " should be of type " + ex.getRequiredType().getName() + ")";

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String input = pathVariables.get("number").toString();

        History requestHistory;
        String path = request.getServletPath();
        String output = "An error occurred while factoring the input: \"" + input +
                "\". " + error + ". Please check \"errors\" in the history table for the exception message.";

        //Create a new history object to be saved to the database
        requestHistory = new History(path, input, output, ex.getMessage());
        historyDao.save(requestHistory);

        return output;
    }

    @Autowired
    private HistoryDao historyDao;
}
