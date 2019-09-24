package com.bcs.restendpoint.model;

import java.util.Objects;

public class Allocation {
    private String sector;
    private Integer assetValue;
    private Double proportion;

    public Allocation() {
    }

    public Allocation(String sector, Integer assetValue, Double proportion) {
        this.sector = sector;
        this.assetValue = assetValue;
        this.proportion = proportion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(Integer assetValue) {
        this.assetValue = assetValue;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return Objects.equals(sector, that.sector) &&
                Objects.equals(assetValue, that.assetValue) &&
                Objects.equals(proportion, that.proportion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sector, assetValue, proportion);
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "sector='" + sector + '\'' +
                ", assetValue=" + assetValue +
                ", proportion=" + proportion +
                '}';
    }
}
