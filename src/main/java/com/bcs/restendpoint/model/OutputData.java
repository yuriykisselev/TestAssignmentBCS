package com.bcs.restendpoint.model;

import java.util.List;
import java.util.Objects;

public class OutputData {
    private Integer value;
    private List<Allocation> allocations;

    public OutputData() {
    }

    public OutputData(Integer value, List<Allocation> allocations) {
        this.value = value;
        this.allocations = allocations;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputData that = (OutputData) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(allocations, that.allocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, allocations);
    }

    @Override
    public String toString() {
        return "OutputStocks{" +
                "value=" + value +
                ", allocations=" + allocations +
                '}';
    }
}
