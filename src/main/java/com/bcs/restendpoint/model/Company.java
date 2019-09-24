package com.bcs.restendpoint.model;

import java.util.Objects;

public class Company {
    private String sector;

    public Company() {
    }

    public Company(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(sector, company.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sector);
    }

    @Override
    public String toString() {
        return "Company{" +
                "sector='" + sector + '\'' +
                '}';
    }
}
