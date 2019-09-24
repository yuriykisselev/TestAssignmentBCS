package com.bcs.restendpoint.model;

import java.util.List;
import java.util.Objects;

public class InputData {
    private List<Stock> stocks;

    public InputData() {
    }

    public InputData(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputData that = (InputData) o;
        return Objects.equals(stocks, that.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stocks);
    }

    @Override
    public String toString() {
        return "InputStocks{" +
                "stocks=" + stocks +
                '}';
    }
}
