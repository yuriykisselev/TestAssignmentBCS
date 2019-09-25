package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CalculatorServiceImpl implements CalculatorService {
    private static String publicToken = "pk_0f4db72e15a840f18e888969c29e1871";
    private static String latestPriceUrlTemplate= "https://cloud.iexapis.com/stable/stock/%s/quote/latestPrice?token=%s";
    private static String sectorUrlTemplate= "https://cloud.iexapis.com/stable/stock/%s/company?token=%s";
    private InputData input;     // входные данные
    private InputData temp;      // промежуточные (входные со стоимостью и секторами)
    private OutputData output;   // выходные данные

    public OutputData calculateData(InputData inputData) throws NullPointerException{
        Map<String, List<Stock>> stocksGroupedBySector = inputData.getStocks().stream()
                .collect(Collectors.groupingBy(Stock::getSector));

        List<Allocation> allocList = stocksGroupedBySector.entrySet().stream()
                .map(entry ->  {
                    Double assetValue = entry.getValue().stream().mapToDouble(Stock::getStockValue).sum();
                    Double proportion = round(assetValue/ inputData.getValue(), 3);
                    return new Allocation(entry.getKey(), assetValue.intValue(), proportion);
                })
                .collect(Collectors.toList());
        output = new OutputData((int) Math.round(inputData.getValue()), allocList);
        return output;
    }

    public InputData getMarketData(InputData inputData) throws NullPointerException{
        input = inputData;
        List<Stock> stocks = inputData.getStocks();
        Double stockPortfolioValue = 0d;
        for (Stock stock : stocks) {
            String symbol = stock.getSymbol();
            Integer volume = stock.getVolume();

            RestTemplate restTemplate = new RestTemplate();
            Double latestPrice = Objects.requireNonNull(restTemplate.getForObject(String.format(latestPriceUrlTemplate, symbol, publicToken), Double.class));
            Company stockCompany = restTemplate.getForObject(String.format(sectorUrlTemplate, symbol, publicToken), Company.class);
            String sector = Objects.requireNonNull(stockCompany).getSector();
            Double stockValue = latestPrice*volume;

            stock.setLatestPrice(latestPrice);
            stock.setSector(sector);
            stock.setStockValue(stockValue);

            stockPortfolioValue += stockValue;
        }
        inputData.setValue((int) Math.round(stockPortfolioValue));
        temp = inputData;
        return temp;
    }

    private Double round (double value, int scale) {
        return new BigDecimal(Double.toString(value)).setScale(scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    public InputData getInput() {
        return input;
    }

    public void setInput(InputData input) {
        this.input = input;
    }

    public InputData getTemp() {
        return temp;
    }

    public void setTemp(InputData temp) {
        this.temp = temp;
    }

    public OutputData getOutput() {
        return output;
    }

    public void setOutput(OutputData output) {
        this.output = output;
    }
}
