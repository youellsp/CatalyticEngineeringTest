package org.pyouells.controllers;

/**
 * Created by pyouells on 9/11/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping({"/",""})
    @ResponseBody
    public String index() {
        return "Catalytic Engineering Test -  " +
                "programmed by Patrick Youells";
    }


}
