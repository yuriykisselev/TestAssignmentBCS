package com.bcs.restendpoint.model;

import java.util.Objects;

public class Stock {
    private String symbol;
    private Integer volume;
    private Double latestPrice;
    private String sector;
    private Double stockValue;      // latestPrice * volume

    public Stock() {
    }

    public Stock(String symbol, Integer volume) {
        this.symbol = symbol;
        this.volume = volume;
    }

    public Stock(String symbol, Integer volume, Double latestPrice, String sector, Double stockValue) {
        this.symbol = symbol;
        this.volume = volume;
        this.latestPrice = latestPrice;
        this.sector = sector;
        this.stockValue = stockValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getStockValue() {
        return stockValue;
    }

    public void setStockValue(Double stockValue) {
        this.stockValue = stockValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(symbol, stock.symbol) &&
                Objects.equals(volume, stock.volume) &&
                Objects.equals(latestPrice, stock.latestPrice) &&
                Objects.equals(sector, stock.sector) &&
                Objects.equals(stockValue, stock.stockValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, volume, latestPrice, sector, stockValue);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", volume=" + volume +
                ", latestPrice=" + latestPrice +
                ", sector='" + sector + '\'' +
                ", stockValue=" + stockValue +
                '}';
    }
}
