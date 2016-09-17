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
import java.util.Map;

/**
 * Created by pyouells on 9/10/16.
 */
@RestController
public class FibonacciController {

    @ApiOperation(value = "Compute fibonacci values for a given number")
    @RequestMapping(value = "service/fibonacci/{number}", method = RequestMethod.GET)
    @ApiResponse(code = 200, message = "Success", response = String.class)
    public String getFibonacciValues(@PathVariable Integer number, HttpServletRequest request){
        History requestHistory;
        String path = "service/fibonacci/";
        String output;

        try {
            String fibonacciSequence = getFibonacciSequence(number);
            output = "The fibonacci series of " + number + " is: " + fibonacciSequence;

            //Create a new history object to save the request history to the database
            requestHistory = new History(path, number.toString(), output, null);
            historyDao.save(requestHistory);

            //return the response back to the consumer
            return requestHistory.getResponse();
        } catch (RuntimeException e) {
            output = "An error occurred while getting the fibonacci sequence for the input: " + number.toString() +
                    " please check 'errors' in the history table.";

            System.out.println(e);
            //Create a new history object to be saved to the database
            requestHistory = new History(path, number.toString(), output, e.getMessage());
            historyDao.save(requestHistory);

            return requestHistory.getResponse();
        }
    }

    private String getFibonacciSequence(Integer number) {
        String output = "";

        for(int i=1; i<=number; i++){
            //Check to see if we're on the last number to avoid trailing comma
            if(i != number){
                output += fibonacciRecursion(i) + ", ";
            }else{
                output += fibonacciRecursion(i);
            }

        }

        return output;
    }

    public static int fibonacciRecursion(int number){
        if(number == 1 || number == 2){
            return 1;
        }

        return fibonacciRecursion(number - 1) + fibonacciRecursion(number - 2);
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception exception) {
        String error = exception.getMessage();

        //Get user input
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String input = pathVariables.get("number").toString();

        History requestHistory;
        String path = request.getServletPath();
        String output = "An error occurred while getting the fibonacci sequence for the input: \"" + input +
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
        String output = "An error occurred while getting the fibonacci sequence for the input: \"" + input +
                "\". " + error + ". Please check \"errors\" in the history table for the exception message.";

        //Create a new history object to be saved to the database
        requestHistory = new History(path, input, output, ex.getMessage());
        historyDao.save(requestHistory);

        return output;
    }

    @Autowired
    private HistoryDao historyDao;
}

