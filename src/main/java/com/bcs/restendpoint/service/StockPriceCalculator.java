package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StockPriceCalculator implements Calculator{
    private static String publicToken = "pk_0f4db72e15a840f18e888969c29e1871";
    private static String latestPriceUrlTemplate= "https://cloud.iexapis.com/stable/stock/%s/quote/latestPrice?token=%s";
    private static String sectorUrlTemplate= "https://cloud.iexapis.com/stable/stock/%s/company?token=%s";

    public OutputData calculateData(InputData inputData) {
        List<Stock> stocks = inputData.getStocks();
        Double stockPortfolioValue = getData(stocks);

        Map<String, List<Stock>> stocksGroupedBySector = stocks.stream()
                .collect(Collectors.groupingBy(Stock::getSector));

        List<Allocation> allocList = stocksGroupedBySector.entrySet().stream()
                .map(entry ->  {
                    Integer assetValue = (int) entry.getValue().stream().mapToDouble(Stock::getStockValue).sum();
                    Double proportion = round(assetValue/ stockPortfolioValue, 3);
                    return new Allocation(entry.getKey(), assetValue, proportion);
                })
                .collect(Collectors.toList());
        return new OutputData((int) Math.round(stockPortfolioValue), allocList);
    }

    private Double getData(List<Stock> stocks) {
        Double stockPortfolioValue = 0d;
        for (Stock stock : stocks) {
            String symbol = stock.getSymbol();
            Integer volume = stock.getVolume();

            RestTemplate restTemplate = new RestTemplate();
            Double latestPrice = restTemplate.getForObject(String.format(latestPriceUrlTemplate, symbol, publicToken), Double.class);
            Company stockCompany = restTemplate.getForObject(String.format(sectorUrlTemplate, symbol, publicToken), Company.class);
            String sector = stockCompany.getSector();
            Double stockValue = latestPrice*volume;

            stock.setLatestPrice(latestPrice);
            stock.setSector(sector);
            stock.setStockValue(stockValue);

            stockPortfolioValue += stockValue;
        }
        return stockPortfolioValue;
    }

    private Double round (double value, int scale) {
        return new BigDecimal(Double.toString(value)).setScale(scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }
}
