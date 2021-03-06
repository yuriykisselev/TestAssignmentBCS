package com.bcs.restendpoint.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allocation {

  private String sector;
  private BigDecimal assetValue;
  private BigDecimal proportion;
}
