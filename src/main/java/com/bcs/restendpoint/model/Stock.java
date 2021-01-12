package com.bcs.restendpoint.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

  private String symbol;
  private BigDecimal volume;
  private BigDecimal latestPrice;
  private String sector;
  private BigDecimal stockValue;      // latestPrice * volume
}
