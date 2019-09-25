package com.bcs.restendpoint.model;

import java.util.List;
import java.util.Objects;

public class InputData {
    private Integer value;
    private List<Stock> stocks;

    public InputData() {
    }

    public InputData(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public InputData(Integer value, List<Stock> stocks) {
        this.value = value;
        this.stocks = stocks;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
        InputData inputData = (InputData) o;
        return Objects.equals(value, inputData.value) &&
                Objects.equals(stocks, inputData.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, stocks);
    }

    @Override
    public String toString() {
        return "InputData{" +
                "value=" + value +
                ", stocks=" + stocks +
                '}';
    }
}
