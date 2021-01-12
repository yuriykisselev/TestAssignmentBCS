package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.Allocation;
import com.bcs.restendpoint.model.Company;
import com.bcs.restendpoint.model.InputData;
import com.bcs.restendpoint.model.OutputData;
import com.bcs.restendpoint.model.Stock;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

  private final ApiClient apiClient;

  @Override
  public OutputData calculateData(InputData inputData) {
    InputData filledData = fillWithMarketData(inputData);
    Map<String, List<Stock>> stocksGroupedBySector = filledData.getStocks().stream()
        .collect(Collectors.groupingBy(Stock::getSector));
    List<Allocation> allocList = stocksGroupedBySector.entrySet().stream()
        .map(entry -> {
          BigDecimal assetValue = entry.getValue().stream()
              .map(Stock::getStockValue)
              .reduce(BigDecimal.ZERO, BigDecimal::add);
          BigDecimal proportion = assetValue.divide(filledData.getValue(), RoundingMode.HALF_DOWN);
          return new Allocation(entry.getKey(), assetValue, proportion);
        })
        .collect(Collectors.toList());
    return new OutputData(filledData.getValue(), allocList);
  }

  private InputData fillWithMarketData(InputData inputData) {
    List<Stock> stocks = inputData.getStocks();
    BigDecimal stockPortfolioValue = BigDecimal.ZERO;
    for (Stock stock : stocks) {
      String symbol = stock.getSymbol();
      BigDecimal latestPrice = apiClient.getLatestPrice(symbol);
      Company stockCompany = apiClient.getCompany(symbol);
      String sector = Objects.requireNonNull(stockCompany).getSector();
      BigDecimal stockValue = latestPrice.multiply(stock.getVolume());
      stock.setLatestPrice(latestPrice);
      stock.setSector(sector);
      stock.setStockValue(stockValue);
      stockPortfolioValue = stockPortfolioValue.add(stockValue);
    }
    inputData.setValue(stockPortfolioValue);
    return inputData;
  }
}
