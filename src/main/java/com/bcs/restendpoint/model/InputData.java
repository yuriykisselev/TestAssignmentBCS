package com.bcs.restendpoint.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InputData {

  private BigDecimal value;
  private List<Stock> stocks;
}
