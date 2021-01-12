package com.bcs.restendpoint.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bcs.restendpoint.model.Company;
import com.bcs.restendpoint.model.InputData;
import com.bcs.restendpoint.model.OutputData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CalculatorServiceTest {

  private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

  @Autowired
  private CalculatorService calculatorService;

  @MockBean
  private ApiClient apiClient;


  @Test
  void shouldCalculateDataCorrectly() throws IOException {
    when(apiClient.getCompany("MDSO"))
        .thenReturn(new Company("Technology Services"));
    when(apiClient.getCompany("AAPL"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("HOG"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("IDRA"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("MRSN"))
        .thenReturn(new Company("Manufacturing"));

    when(apiClient.getLatestPrice("AAPL"))
        .thenReturn(new BigDecimal("128.885"));
    when(apiClient.getLatestPrice("HOG"))
        .thenReturn(new BigDecimal("39.495"));
    when(apiClient.getLatestPrice("MDSO"))
        .thenReturn(new BigDecimal("92.22"));
    when(apiClient.getLatestPrice("IDRA"))
        .thenReturn(new BigDecimal("4.29"));
    when(apiClient.getLatestPrice("MRSN"))
        .thenReturn(new BigDecimal("18.53"));

    InputData inputData = objectMapper
        .readValue(new File("src/test/resources/input.json"), InputData.class);
    OutputData result = calculatorService.calculateData(inputData);
    OutputData expected = objectMapper
        .readValue(new File("src/test/resources/output_1.json"), OutputData.class);
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void shouldCalculateDataCorrectlyForDifferentSectors() throws IOException {
    when(apiClient.getCompany("MDSO"))
        .thenReturn(new Company("Technology Services"));
    when(apiClient.getCompany("AAPL"))
        .thenReturn(new Company("Technology Services"));
    when(apiClient.getCompany("HOG"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("IDRA"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("MRSN"))
        .thenReturn(new Company("Manufacturing"));

    when(apiClient.getLatestPrice("AAPL"))
        .thenReturn(new BigDecimal("128.885"));
    when(apiClient.getLatestPrice("HOG"))
        .thenReturn(new BigDecimal("39.495"));
    when(apiClient.getLatestPrice("MDSO"))
        .thenReturn(new BigDecimal("92.22"));
    when(apiClient.getLatestPrice("IDRA"))
        .thenReturn(new BigDecimal("4.29"));
    when(apiClient.getLatestPrice("MRSN"))
        .thenReturn(new BigDecimal("18.53"));

    InputData inputData = objectMapper
        .readValue(new File("src/test/resources/input.json"), InputData.class);
    OutputData result = calculatorService.calculateData(inputData);
    OutputData expected = objectMapper
        .readValue(new File("src/test/resources/output_2.json"), OutputData.class);
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void shouldThrowExceptionWhenApiClientReturnsZeroPrices() throws IOException {
    when(apiClient.getCompany("MDSO"))
        .thenReturn(new Company("Technology Services"));
    when(apiClient.getCompany("AAPL"))
        .thenReturn(new Company("Technology Services"));
    when(apiClient.getCompany("HOG"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("IDRA"))
        .thenReturn(new Company("Manufacturing"));
    when(apiClient.getCompany("MRSN"))
        .thenReturn(new Company("Manufacturing"));

    when(apiClient.getLatestPrice(any()))
        .thenReturn(new BigDecimal("0"));

    InputData inputData = objectMapper
        .readValue(new File("src/test/resources/input.json"), InputData.class);
    assertThrows(ArithmeticException.class, () -> calculatorService.calculateData(inputData));
  }

  @Test
  void shouldThrowExceptionWhenApiClientReturnsNullCompanies() throws IOException {
    when(apiClient.getCompany(any()))
        .thenReturn(null);

    when(apiClient.getLatestPrice("AAPL"))
        .thenReturn(new BigDecimal("128.885"));
    when(apiClient.getLatestPrice("HOG"))
        .thenReturn(new BigDecimal("39.495"));
    when(apiClient.getLatestPrice("MDSO"))
        .thenReturn(new BigDecimal("92.22"));
    when(apiClient.getLatestPrice("IDRA"))
        .thenReturn(new BigDecimal("4.29"));
    when(apiClient.getLatestPrice("MRSN"))
        .thenReturn(new BigDecimal("18.53"));

    InputData inputData = objectMapper
        .readValue(new File("src/test/resources/input.json"), InputData.class);
    assertThrows(NullPointerException.class, () -> calculatorService.calculateData(inputData));
  }
}