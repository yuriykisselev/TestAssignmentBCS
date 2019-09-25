package com.bcs.restendpoint.controllers;

import com.bcs.restendpoint.model.*;
import com.bcs.restendpoint.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @ResponseBody
    @RequestMapping("/calculate")
    public OutputData calculate(@RequestBody InputData inputData) {
        return calculatorService.calculateData(inputData);
    }

}
