package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.Company;
import java.math.BigDecimal;

/**
 * Trading API https://iexcloud.io
 */
public interface ApiClient {

  BigDecimal getLatestPrice(String symbol);

  Company getCompany(String symbol);
}
