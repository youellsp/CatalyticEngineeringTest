package org.pyouells.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
public class PalindromeController {

    @ApiOperation(value = "Pass in a sentence and return if the sentence is a palindrome or not")
    @RequestMapping(value = "service/palindrome/{string}", method = RequestMethod.GET)
    @ApiResponse(code = 200, message = "Success", response = String.class)
    public String getPalindrome(@PathVariable String string, HttpServletRequest request){
        History requestHistory;
        String path = "service/palindrome/";
        String output;

        try {
            //Save the original string and strip out whitespace and non-alphabetic characters
            String strippedString = string.toLowerCase().replaceAll("[^a-z0-9]", "");
            Boolean isPalindrome = strippedString.equals(new StringBuilder(strippedString).reverse().toString());

            if(isPalindrome){
                output = "'" + string + "'" + " is a Palindrome";
            }else{
                output = "'" + string + "'" + " is not a Palindrome";
            }

            //Create a new history object to save the request history to the database
            requestHistory = new History(path, string, output, null);
            historyDao.save(requestHistory);

            //return the response back to the consumer
            return requestHistory.getResponse();
        } catch (RuntimeException e) {
            output = "An error occurred while determining if the input: " + string.toString() +
                    " was a Palindrome. Please check 'errors' in the history table.";

            System.out.println(e);
            //Create a new history object to be saved to the database
            requestHistory = new History(path, string.toString(), output, e.getMessage());
            historyDao.save(requestHistory);

            return requestHistory.getResponse();
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception exception) {
        String error = exception.getMessage();

        //Get user input
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String input = pathVariables.get("string").toString();

        History requestHistory;
        String path = request.getServletPath();
        String output = "An error occurred while determining if the input: " + input.toString() +
                " is a Palindrome. Please check \"errors\" in the history table.";

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

        String input = pathVariables.get("string").toString();
        History requestHistory;
        String path = request.getServletPath();
        String output = "An error occurred while determining if the input: \"" + input +
                "\" is a Palindrome. " + error + ". Please check \"errors\" in the history table for the exception message.";

        //Create a new history object to be saved to the database
        requestHistory = new History(path, input, output, ex.getMessage());
        historyDao.save(requestHistory);

        return output;
    }

    @Autowired
    HistoryDao historyDao;
}
