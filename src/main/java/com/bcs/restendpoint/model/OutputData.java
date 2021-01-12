package com.bcs.restendpoint.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputData {

  private BigDecimal value;
  private List<Allocation> allocations;
}
