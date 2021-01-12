package com.bcs.restendpoint.controller;

import com.bcs.restendpoint.model.InputData;
import com.bcs.restendpoint.model.OutputData;
import com.bcs.restendpoint.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CalculatorController extends BaseController {

  private final CalculatorService calculatorService;

  @PostMapping("/calculate")
  public OutputData calculate(@RequestBody InputData inputData) {
    return calculatorService.calculateData(inputData);
  }
}
