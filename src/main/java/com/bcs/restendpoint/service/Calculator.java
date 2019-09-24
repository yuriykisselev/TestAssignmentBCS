package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.InputData;
import com.bcs.restendpoint.model.OutputData;
import org.springframework.stereotype.Service;

@Service
public interface Calculator {
    OutputData calculateData(InputData inputData);
}
