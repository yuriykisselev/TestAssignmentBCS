package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.InputData;
import com.bcs.restendpoint.model.OutputData;

public interface CalculatorService {

  OutputData calculateData(InputData inputData);
}
