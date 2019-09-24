package com.bcs.restendpoint.controllers;

import com.bcs.restendpoint.model.*;
import com.bcs.restendpoint.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EndPointController {

    @Autowired
    private Calculator calculator;

    @ResponseBody
    @RequestMapping("/calculate")
    public OutputData calculate(@RequestBody InputData inputData) {
        return calculator.calculateData(inputData);
    }
}
