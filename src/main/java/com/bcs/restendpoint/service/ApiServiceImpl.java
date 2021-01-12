package com.bcs.restendpoint.service;

import com.bcs.restendpoint.model.Company;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiClient {

  @Value("${publicToken}")
  private String PUBLIC_TOKEN;
  @Value("${latestPriceUrlTemplate}")
  private String LATEST_PRICE_URL_TEMPLATE;
  @Value("${sectorUrlTemplate}")
  private String SECTOR_URL_TEMPLATE;

  @Override
  public BigDecimal getLatestPrice(String symbol) {
    RestTemplate restTemplate = new RestTemplate();
    return Objects.requireNonNull(restTemplate.getForObject(String.format(
        LATEST_PRICE_URL_TEMPLATE, symbol,
        PUBLIC_TOKEN), BigDecimal.class));
  }

  @Override
  public Company getCompany(String symbol) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(String.format(SECTOR_URL_TEMPLATE, symbol,
        PUBLIC_TOKEN), Company.class);
  }
}
